import os
import asyncio
import json
import io
from typing import Optional

from fastapi import FastAPI, UploadFile, File, status
from fastapi.responses import JSONResponse
import google.generativeai as genai
from dotenv import load_dotenv
from PIL import Image

# Load environment variables
load_dotenv()

GEMINI_API_KEY = os.getenv("GEMINI_API_KEY")
TIMEOUT_SECONDS = int(os.getenv("TIMEOUT_SECONDS", 30))

if not GEMINI_API_KEY:
    print("Warning: GEMINI_API_KEY not set in environment variables.")
else:
    genai.configure(api_key=GEMINI_API_KEY)

# Initialize the model
# Using gemini-1.5-flash as it is cost-effective and supports multimodal input
model = genai.GenerativeModel(os.getenv("GEMINI_MODEL", "gemini-2.5-flash-lite"))

app = FastAPI(title="Pet Explorer AI Service")

@app.get("/")
async def root():
    return {"message": "Pet Explorer AI Service is running"}

async def generate_pet_description(image_bytes: bytes) -> dict:
    try:
        # Load image from bytes
        image = Image.open(io.BytesIO(image_bytes))
        
        # Read prompt from file
        try:
            with open("prompt.md", "r", encoding="utf-8") as f:
                prompt = f.read()
        except FileNotFoundError:
            # Fallback prompt if file is missing
            prompt = """
            Ești un expert zoolog și specialist în identificarea animalelor pierdute.
            Analizează imaginea furnizată.
            Returnează un JSON cu { "is_pet": boolean, "description": "string", "reason": "string" }.
            """

        # Generate content asynchronously
        response = await model.generate_content_async([prompt, image])
        
        # Check if response was blocked or empty
        if not response.parts:
             raise ValueError("Gemini response was empty or blocked by safety filters.")

        text_response = response.text.strip()
        
        # Clean up markdown if present
        if text_response.startswith("```json"):
            text_response = text_response[7:]
        if text_response.startswith("```"):
            text_response = text_response[3:]
        if text_response.endswith("```"):
            text_response = text_response[:-3]
            
        return json.loads(text_response)

    except Exception as e:
        raise e

@app.post("/analyze-pet")
async def analyze_pet(file: UploadFile = File(...)):
    if not GEMINI_API_KEY:
        return JSONResponse(
            status_code=status.HTTP_503_SERVICE_UNAVAILABLE,
            content={"status": "error", "message": "Service configuration error: API Key missing"}
        )

    # Validate file type
    if not file.content_type.startswith("image/"):
        return JSONResponse(
            status_code=status.HTTP_400_BAD_REQUEST,
            content={"status": "error", "message": "File provided is not an image."}
        )

    try:
        contents = await file.read()
        
        # Implement Timeout
        try:
            result = await asyncio.wait_for(generate_pet_description(contents), timeout=TIMEOUT_SECONDS)
        except asyncio.TimeoutError:
             return JSONResponse(
                status_code=status.HTTP_504_GATEWAY_TIMEOUT,
                content={
                    "status": "fallback",
                    "message": f"Request took longer than {TIMEOUT_SECONDS} seconds."
                }
            )
            
        if not result.get("is_pet"):
            return JSONResponse(
                status_code=status.HTTP_200_OK, 
                content={
                    "status": "fallback",
                    "message": "Image is not recognized as a pet or animal.",
                    "details": result.get("reason")
                }
            )
            
        return {
            "status": "success",
            "description": result.get("description")
        }

    except json.JSONDecodeError:
        return JSONResponse(
            status_code=status.HTTP_500_INTERNAL_SERVER_ERROR,
            content={
                "status": "error",
                "message": "Failed to parse AI response."
            }
        )
    except Exception as e:
        error_message = str(e)
        
        # Check for Quota Exceeded (ResourceExhausted)
        if "429" in error_message or "ResourceExhausted" in error_message or "Quota exceeded" in error_message:
             return JSONResponse(
                status_code=status.HTTP_429_TOO_MANY_REQUESTS,
                content={
                    "status": "fallback",
                    "message": "Service quota exceeded. Please try again later."
                }
            )
        
        # General error fallback
        return JSONResponse(
            status_code=status.HTTP_500_INTERNAL_SERVER_ERROR,
            content={
                "status": "error",
                "message": f"An error occurred processing the image: {error_message}"
            }
        )

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8000)
