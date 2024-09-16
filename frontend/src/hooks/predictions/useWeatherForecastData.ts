import {useQuery} from "react-query";
import {getWeatherForecast} from "../../services/API";
import {AIModelPredictionInput} from "../../model/predictions/AIModelPredictionInput";
import {WeatherForecastData} from "../../model/predictions/WeatherForecastData";

export function useWeatherForecastData(lat: number, lon: number, cnt: number, apiKey: string) {
    const {
        isLoading: isDoingGetWeatherForecastData,
        isError: isErrorGetWeatherForecastData,
        data: weatherForecast
    } = useQuery( {
        queryKey: ['weather'],
        queryFn: () => getWeatherForecast(lat, lon, cnt, apiKey)
    })

    return {
        isLoadingWeatherForecast: isDoingGetWeatherForecastData,
        isErrorLoadingWeatherForecast: isErrorGetWeatherForecastData,
        weatherForecast: weatherForecast
    }
}