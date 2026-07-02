import requests

ML_API_URL = "http://localhost:8000/predict"


def predict_price(data):
    response = requests.post(
        ML_API_URL,
        json=data
    )

    response.raise_for_status()

    return response.json()