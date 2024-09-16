interface ListMain {
    temp: number,
    feels_like: number,
    temp_min: number,
    temp_max: number,
    pressure: number,
    sea_level: number,
    grnd_level: number,
    humidity: number,
    temp_kf: number
}

interface ListWeather {
    id: number,
    main: string,
    description: string,
    icon: string
}

interface ListClouds {
    all: number
}

interface ListWind {
    speed: number,
    deg: number,
    gust: number
}

interface ListRain {
    '3h': number
}

interface ListSys {
    pod: string
}

interface List {
    dt: number,
    main: ListMain,
    weather: ListWeather[],
    clouds: ListClouds,
    wind: ListWind,
    visibility: number,
    pop: number,
    rain: ListRain,
    sys: ListSys,
    dt_text: string
}

interface CityCoordinates {
    lat: number,
    lon: number
}

interface City {
    id: number,
    name: string,
    coord: CityCoordinates,
    country: string,
    population: number,
    timezone: number,
    sunrise: number,
    sunset: number
}

export interface WeatherForecastData {
    cod: string,
    message: number,
    cnt: number,
    list: List[],
    city: City
}