import requests
import os
import glob

url = "http://localhost:8000/analyze-pet"
uploads_dir = r"C:\Users\redis\Desktop\Projects\PetExplorerBackend\uploads"

# 1. Get ID from keyboard
file_id = input("Enter the image ID (or part of the filename): ").strip()

if not file_id:
    print("Error: ID cannot be empty.")
    exit()

# 2. Find the file in the uploads directory
# This looks for any file containing the ID in its name
search_pattern = os.path.join(uploads_dir, f"*{file_id}*")
found_files = glob.glob(search_pattern)

# Filter for common image extensions
valid_extensions = ('.jpg', '.jpeg', '.png', '.webp')
image_files = [f for f in found_files if f.lower().endswith(valid_extensions)]

if not image_files:
    print(f"No image found matching '{file_id}' in {uploads_dir}")
    exit()

# Pick the first match
image_path = image_files[0]
print(f"Processing image: {os.path.basename(image_path)}")

try:
    with open(image_path, "rb") as f:
        # Explicitly setting filename and content_type ensures the server receives the headers correctly
        files = {"file": (os.path.basename(image_path), f, "image/jpeg")}
        response = requests.post(url, files=files)
    
    print(f"Status Code: {response.status_code}")
    print("Response:")
    print(response.json())

except Exception as e:
        print(f"An error occurred: {e}").get("description")