import json
import joblib
import pandas as pd


class HousingPriceModel:

    def __init__(self):
        self.model = joblib.load("model.pkl")

        with open("metrics.json") as file:
            self.metrics = json.load(file)

    def predict(self, input_data):

        dataframe = pd.DataFrame(input_data)

        prediction = self.model.predict(dataframe)

        return prediction.tolist()

    def get_metrics(self):
        return self.metrics


model_service = HousingPriceModel()