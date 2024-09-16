import axios from "axios"
import { Station } from '../model/station/Station'
import {WeatherForecastData} from "../model/predictions/WeatherForecastData";
import {AIModelPredictionInput} from "../model/predictions/AIModelPredictionInput";
import {AIModelPredictionOutput} from "../model/predictions/AIModelPredictionOutput";
import {Bike} from "../model/station/Bike";
import {DefectFormInputs} from "../components/pages/defect-report/DefectFormInputs";
import {Ride} from "../model/customer/Ride";
import {MaintenanceVehicleDefectReport} from "../model/maintenance-reports/MaintenanceVehicleDefectReport";

// Defaults
axios.defaults.headers.post["Content-Type"] = "application/json";

// Station API functions
const jsonServer = axios.create({
    // baseURL: 'http://localhost:8080'
    baseURL: 'https://spring-backend-dot-kdg-acs3-integration-5-team-2.ew.r.appspot.com'
});

const STATIONS_URL = '/api/stations/'

export async function getStation(id: String) {
    const response = await jsonServer.get<Station>(STATIONS_URL + id)
    return response.data
}

export async function getStations() {
    const response = await jsonServer.get<Station[]>(STATIONS_URL)
    return response.data
}

// Weather API functions
const weatherAPI = axios.create({
    baseURL: 'https://api.openweathermap.org'
});

export async function getWeatherForecast(lat: Number, lon: Number, cnt: Number, apiKey: String) {
    const response = await weatherAPI.get<WeatherForecastData>(`/data/2.5/forecast?lat=${lat}&lon=${lon}&cnt=${cnt}&appid=${apiKey}`)
    return response.data
}


// Python server API functions
const pythonServer = axios.create({
    baseURL: "https://python-ai-flex-dot-kdg-acs3-integration-5-team-2.ew.r.appspot.com"
    // baseURL: 'http://localhost:5000'
});

const TRIP_COUNT_PREDICTION_URL = '/api/prediction/trip-count'
const MEAN_TRIP_DURATION_PREDICTION_SVR_URL = '/api/prediction/mean-trip-duration/svr'
const MEAN_TRIP_DURATION_PREDICTION_NN_URL = '/api/prediction/mean-trip-duration/nn'

export async function getTripDurationPredictionSVR(input: AIModelPredictionInput) {
    const response = await pythonServer.post<AIModelPredictionOutput>(MEAN_TRIP_DURATION_PREDICTION_SVR_URL, JSON.stringify(input))
    return response.data
}

export async function getTripDurationPredictionNN(input: AIModelPredictionInput) {
    const response = await pythonServer.post<AIModelPredictionOutput>(MEAN_TRIP_DURATION_PREDICTION_NN_URL, JSON.stringify(input))
    return response.data
}

export async function getTripCountPrediction(input: AIModelPredictionInput) {
    const response = await pythonServer.post<AIModelPredictionOutput>(TRIP_COUNT_PREDICTION_URL, JSON.stringify(input))
    return response.data
}


// Backend REST API server functions
const backendServer = axios.create({
    // baseURL: 'http://localhost:8080'
    baseURL: 'https://spring-backend-dot-kdg-acs3-integration-5-team-2.ew.r.appspot.com'
})

const GET_ALL_BIKES_URL = '/api/bikes'

export async function getAllBikes() {
    const response = await backendServer.get<Bike[]>(GET_ALL_BIKES_URL)
    return response.data
}

export async function startBikeRide(stationUUID: string) {
    try {
        await jsonServer.post(`/api/stations/ride/start/2ce1dda3-71a6-4383-bbf2-133318d90ad8/${stationUUID}`);
    } catch (error) {
        console.log(error);
    }
}

export async function endBikeRide(stationUUID: string) {
    await backendServer.post(`/api/stations/ride/end/2ce1dda3-71a6-4383-bbf2-133318d90ad8/47910a7b-4457-498e-9d9d-e5d43b74f2c6/2`)
}


export async function createVehicleDefectReport(defectReport: DefectFormInputs) {
    // console.log('data before request', defectReport)
    await backendServer.post(`/api/vehicle-defect-report`, defectReport)
}

export async function getAllRidesFromCustomer(customerUUID: string) {
    const response = await backendServer.get<Ride[]>('api/customer/rides/ridehistory/' + customerUUID)
    return response.data
}

export async function getOpenMaintenanceVehicleDefectReports(){
    const response = await backendServer.get<MaintenanceVehicleDefectReport[]>('/maintenance/api/vehicle-defect-reports/?status=OPEN')
    return response.data
}

export async function updateMaintenanceVehicleDefectReportStatus(uuid: string, status: string){
    await backendServer.patch(`/maintenance/api/vehicle-defect-report/${uuid}`, {status: status})
}

export async function getAvailableDocks(stationId: string, status: string) {
    const response = await backendServer.get(`/api/stations/${stationId}/docks/${status}`)
    return response.data
}
