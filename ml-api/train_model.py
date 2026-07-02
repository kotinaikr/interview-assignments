import json
import joblib
import pandas as pd

from sklearn.linear_model import LinearRegression
from sklearn.metrics import mean_absolute_error
from sklearn.metrics import mean_squared_error
from sklearn.metrics import r2_score
from sklearn.model_selection import train_test_split


DATASET_PATH = "TestDataForPrediction.csv"

TARGET_COLUMN = "price"


df = pd.read_csv(DATASET_PATH)

DROP_COLUMNS = ["id", TARGET_COLUMN]

X = df.drop(columns=DROP_COLUMNS)

y = df[TARGET_COLUMN]


X_train, X_test, y_train, y_test = train_test_split(
    X,
    y,
    test_size=0.20,
    random_state=42
)

model = LinearRegression()

model.fit(X_train, y_train)

predictions = model.predict(X_test)

metrics = {
    "mae": float(mean_absolute_error(y_test, predictions)),
    "rmse": float(mean_squared_error(y_test, predictions) ** 0.5),
    "r2_score": float(r2_score(y_test, predictions)),
    "coefficients": dict(zip(X.columns, model.coef_)),
    "intercept": float(model.intercept_)
}

joblib.dump(model, "model.pkl")

with open("metrics.json", "w") as file:
    json.dump(metrics, file, indent=4)

print("Model trained successfully.")