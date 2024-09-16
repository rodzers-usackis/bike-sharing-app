export interface AIModelPredictionInput {
    "date": Date,
    "part_of_day": number,
    "mean_temperature_k": number,
    "mean_wind_speed_mph": number,
    "precipitation_inches": number,
    "cloud_cover": number,
    "mean_duration_yesterday": number,
}