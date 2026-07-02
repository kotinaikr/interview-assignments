from fastapi import FastAPI
from schemas import HouseFeatures
from service import predict_price

app = FastAPI(
    title="Property Estimator API"
)

prediction_history = []


@app.get("/health")
def health():
    return {
        "status": "UP"
    }


@app.post("/estimate")
def estimate(property_details: HouseFeatures):

    result = predict_price(
        property_details.model_dump()
    )

    prediction_history.append({
        "input": property_details.model_dump(),
        "prediction": result
    })

    return result

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


@app.get("/history")
def history():
    return prediction_history

# Example response
# [
#   {
#     "input": {
#       "square_footage": 1800,
#       "bedrooms": 3,
#       "bathrooms": 2,
#       "year_built": 2015,
#       "lot_size": 4500,
#       "distance_to_city_center": 4.2,
#       "school_rating": 8.1
#     },
#     "prediction": {
#       "predicted_price": 287543.42
#     }
#   }
# ]