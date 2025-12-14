# Pet Explorer AI Service

This is a FastAPI service that uses Google's Gemini AI to analyze images of pets and generate descriptions.

## Setup

1.  **Install Python 3.9+**
2.  **Install dependencies:**
    ```bash
    pip install -r requirements.txt
    ```
3.  **Configure Environment:**
    - Copy `.env.example` to `.env`
    - Add your Gemini API Key to `.env`: `GEMINI_API_KEY=...`
    - (Optional) Adjust `TIMEOUT_SECONDS` in `.env`.

## Running the Service

```bash
python main.py
```
Or using uvicorn directly:
```bash
uvicorn main:app --reload
```

The service will start at `http://localhost:8000`.

## API Usage

Endpoint : '/python_app/analyze-pet'