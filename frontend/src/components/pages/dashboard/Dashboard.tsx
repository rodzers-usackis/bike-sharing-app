import React, {useEffect, useState} from 'react';
import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    PointElement,
    ArcElement,
    LineElement,
    Title,
    Tooltip,
    Legend,
    Colors
} from 'chart.js';
import {Doughnut, Line} from 'react-chartjs-2';
import {faker} from '@faker-js/faker';
import {Alert, CircularProgress, Grid, useMediaQuery} from "@mui/material";
import AppWidgetSummary from "./AppWidgetSummary";
import Box from "@mui/material/Box";
import * as MaterialIcons from 'react-icons/md'
import {useWeatherForecastData} from "../../../hooks/predictions/useWeatherForecastData";
import {AIModelPredictionInput} from "../../../model/predictions/AIModelPredictionInput";
import {useTripCountPrediction} from "../../../hooks/predictions/useTripCountPrediction";
import {useTripDurationPredictionSVR} from "../../../hooks/predictions/useTripDurationPredictionSVR";
import {useTripDurationPredictionNN} from "../../../hooks/predictions/useTripDurationPredictionNN";

ChartJS.register(
    ArcElement,
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend,
    Colors
);

export const linechart_options_1 = {
    responsive: true,
    layout: {
        padding: {
            left: 0,
            right: 0,
            top: 0,
            bottom: 0,
        },
        margin: {
            left: 0,
            right: 0,
            top: 0,
            bottom: 0,
        },
    },
    plugins: {
        legend: {
            display: false,
        },
        title: {
            display: true,
            text: 'Predicted Trip Count',
        },
    },
};

export const linechart_options_2 = {
    responsive: true,
    layout: {
        padding: {
            left: 0,
            right: 0,
            top: 0,
            bottom: 0,
        },
        margin: {
            left: 0,
            right: 0,
            top: 0,
            bottom: 0,
        },
    },
    plugins: {
        legend: {
            display: false,
        },
        title: {
            display: true,
            text: 'Predicted Trip Mean Duration',
        },
    },
};

export const doughnut_options_1 = {
    responsive: true,
    maintainAspectRatio: true,
    plugins: {
        legend: {
            display: true,
            position: "bottom" as const
        },
        legendDistance: {
            padding: 20
        },
        title: {
            display: true,
            text: 'Dock Status'
        },
        titleDistance: {
            padding: 20
        }
    },
}

export const doughnut_options_2 = {
    responsive: true,
    maintainAspectRatio: true,
    plugins: {
        legend: {
            display: true,
            position: "bottom" as const
        },
        legendDistance: {
            padding: 20
        },
        title: {
            display: true,
            text: 'Bike Status'
        },
        titleDistance: {
            padding: 20
        }
    },
}

export const labels = ['09:00', '12:00', '15:00', '18:00', '21:00', '24:00'];

export const linechart_data_1 = {
    labels,
    datasets: [
        {
            label: 'Dataset 1',
            data: labels.map(() => faker.datatype.number({min: 200, max: 1000})),
            borderColor: 'rgb(255, 99, 132)',
            backgroundColor: 'rgba(255, 99, 132, 0.5)',
        },
    ],
};

export const linechart_data_2 = {
    labels,
    datasets: [
        {
            label: 'Dataset 1',
            data: labels.map(() => faker.datatype.number({min: 200, max: 1000})),
            borderColor: 'rgb(54, 162, 235)',
            backgroundColor: 'rgba(54, 162, 235, 0.5)',
        },
    ],
};

export const doughnut_data_1 = {
    labels: ['In-Use', 'Unused'],
    datasets: [
        {
            label: '# of docks',
            data: [12, 19],
            borderWidth: 1,
            plugins: {
                colors: {
                    enabled: true
                }
            }
        },
    ],
};

export const doughnut_data_2 = {
    labels: ['In-Use', 'Available', 'Broken', 'In Maintenance'],
    datasets: [
        {
            label: '# of bikes',
            data: [42, 19, 5, 6],
            borderWidth: 1,
            plugins: {
                colors: {
                    enabled: true
                }
            }
        },
    ],
};

export default function Dashboard() {
    const matches = useMediaQuery('(max-width:599px)');
    /*
    Weather variables
    For now, since we're working with data from San Francisco, I'm setting the coordinates statically.
    Otherwise, I would request the geolocation of the user using the application.
    */
    const lat = 37.773972
    const lon = -122.431297
    // The count is the number of days we will collect the data for, in our case 7.
    const cnt = 1

    // const apiKey = process.env.NEXT_PUBLIC_WEATHER_API_KEY
    const apiKey = 'ff81fe37ad2b546130b7cbcb331aa72c'
    const [inputData, setInputData] = useState({} as AIModelPredictionInput);

    const {
        isLoadingWeatherForecast,
        isErrorLoadingWeatherForecast,
        weatherForecast
    } = useWeatherForecastData(lat, lon, cnt, apiKey)

    useEffect(() => {
        if (weatherForecast) {
            /*
            Processing the collected data from the API call
            The data is split by 3 hours, so 8 moments per day.
            Our day-split:
            21:00 - 06:00
            06:00 - 10:00
            10:00 - 17:00
            17:00 - 21:00
            */

            /*
             Currently, these are static, but once me and Dominik decide on the parameters,
             they will be acquired from the 'weatherForecast' variable
            */
            const data: any = {
                date: new Date("2016-02-02"),
                part_of_day: 3,
                mean_temperature_k: weatherForecast?.list[0].main.temp_kf,
                mean_wind_speed_mph: weatherForecast?.list[0].wind.speed,
                precipitation_inches: weatherForecast?.list[0].pop,
                cloud_cover: weatherForecast?.list[0].clouds.all,
                mean_duration_yesterday: 1000
            }
            setInputData(data)
        }
    }, [weatherForecast])


    const {
        isLoadingTripDurationPredictionSVR,
        isErrorTripDurationPredictionSVR,
        tripDurationPredictionSVR,
    } = useTripDurationPredictionSVR(inputData);

    const {
        isLoadingTripDurationPredictionNN,
        isErrorTripDurationPredictionNN,
        tripDurationPredictionNN
    } = useTripDurationPredictionNN(inputData);


    if (isErrorLoadingWeatherForecast) {
        return <Alert severity="error">Weather could not be loaded.</Alert>;
    }

    if (isErrorTripDurationPredictionNN || isErrorTripDurationPredictionSVR) {
        return <Alert severity="error">Predictions could not be loaded.</Alert>;
    }

    if (isLoadingWeatherForecast || isLoadingTripDurationPredictionNN || isLoadingTripDurationPredictionSVR) {
        return (
            <div className={'prediction-container-div'} style={{
                height: matches ? 'calc(100vh - 112px)' : 'calc(100vh - 64px)',
                display: 'flex', justifyContent: 'center', alignItems: 'center'
            }}
            >
                <CircularProgress sx={{display: "block"}}/>
            </div>
        )
    }

    return (
        <>
            <Box>
                <h1 className={'dashboard-title'}>Overview</h1>
                <h3 className={'dashboard-subtitle'}>Welcome back, Manager #21!</h3>
                <Box>
                    <Grid container sx={{
                        justifyContent: 'center',
                        alignItems: 'center',
                        alignContent: 'center',
                    }}>
                        <Grid item xs={6} sm={5} md={3} lg={3} px={4} py={2}>
                            <AppWidgetSummary color={'red'} title="Predicted Mean Trip Duration SVR" total={Math.round(tripDurationPredictionSVR!.prediction * 100) / 100}
                                              icon={<MaterialIcons.MdHourglassBottom/>} added_text={''} sx={{}}/>
                        </Grid>
                        <Grid item xs={6} sm={5} md={3} lg={3} px={4} py={2}>
                            <AppWidgetSummary color={'red'} title="Predicted Mean Trip Duration NN" total={Math.round(tripDurationPredictionNN!.prediction * 100) / 100}
                                              icon={<MaterialIcons.MdHourglassBottom/>} added_text={''} sx={{}}/>
                        </Grid>
                        <Grid item xs={6} sm={5} md={3} lg={3} px={4} py={2}>
                            <AppWidgetSummary color={'red'} title="Weekly Earnings" total={5673}
                                              icon={<MaterialIcons.MdEuroSymbol/>} added_text={''} sx={{}}/>
                        </Grid>
                        <Grid item xs={6} sm={5} md={3} lg={3} px={4} py={2}>
                            <AppWidgetSummary color={'red'} title="Monthly Earnings" total={31960}
                                              icon={<MaterialIcons.MdEuroSymbol/>} added_text={''} sx={{}}/>
                        </Grid>
                    </Grid>
                    <Grid container sx={{
                        justifyContent: 'center',
                        alignItems: 'center',
                    }}>
                        <Grid className={'linechart'} item xs={12} md={6} lg={6} xl={6} px={2}>
                            <Grid container sx={{minHeight: '30vh', justifyContent: 'center'}}>
                                <Line options={linechart_options_1} data={linechart_data_1}></Line>
                            </Grid>
                        </Grid>
                        <Grid className={'linechart'} item xs={12} md={6} lg={6} xl={6} px={2}>
                            <Grid container sx={{minHeight: '30vh', justifyContent: 'center'}}>
                                <Line options={linechart_options_2} data={linechart_data_2}></Line>
                            </Grid>
                        </Grid>
                    </Grid>

                    <Grid container sx={{justifyContent: 'space-evenly'}}
                          style={{paddingBottom: matches ? '56px' : '0'}}>
                        <Grid item xs={6} md={4} lg={3} xl={3}>
                            <Grid container sx={{justifyContent: 'center'}}>
                                <Doughnut options={doughnut_options_1} data={doughnut_data_1}></Doughnut>
                            </Grid>
                        </Grid>
                        <Grid item xs={6} md={4} lg={3} xl={3}>
                            <Grid container sx={{justifyContent: 'center'}}>
                                <Doughnut options={doughnut_options_2} data={doughnut_data_2}></Doughnut>
                            </Grid>
                        </Grid>
                    </Grid>
                </Box>
            </Box>
        </>
    );
}