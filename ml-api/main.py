from fastapi import FastAPI

from model_service import model_service
from schemas import BatchPredictionRequest
from schemas import HouseFeatures

app = FastAPI(
    title="Housing Price Prediction API",
    version="1.0.0"
)


@app.get("/")
def root():

    return {
        "message": "Housing Price Prediction API"
    }


@app.get("/health")
def health():

    return {
        "status": "UP"
    }


@app.get("/model-info")
def model_info():

    return model_service.get_metrics()


@app.post("/predict")
def predict(property_details: HouseFeatures):

    prediction = model_service.predict(
        [property_details.model_dump()]
    )

    return {
        "predicted_price": prediction[0]
    }

# Example request body
# {
#   "square_footage": 1800,
#   "bedrooms": 3,
#   "bathrooms": 2,
#   "year_built": 2015,
#   "lot_size": 4500,
#   "distance_to_city_center": 4.2,
#   "school_rating": 8.1
# }

@app.post("/predict/batch")
def batch_predict(request: BatchPredictionRequest):

    records = [
        property.model_dump()
        for property in request.properties
    ]

    predictions = model_service.predict(records)

    return {
        "predictions": predictions
    }

# Example request body
# {
#   "properties": [
#     {
#       "square_footage": 1800,
#       "bedrooms": 3,
#       "bathrooms": 2,
#       "year_built": 2015,
#       "lot_size": 4500,
#       "distance_to_city_center": 4.2,
#       "school_rating": 8.1
#     },
#     {
#       "square_footage": 2200,
#       "bedrooms": 4,
#       "bathrooms": 3,
#       "year_built": 2018,
#       "lot_size": 6200,
#       "distance_to_city_center": 6.0,
#       "school_rating": 8.8
#     }
#   ]
# }