import {useQuery} from 'react-query'
import {getTripDurationPredictionSVR} from '../../services/API'
import {AIModelPredictionInput} from "../../model/predictions/AIModelPredictionInput";

export function useTripDurationPredictionSVR(input: AIModelPredictionInput) {

    const {
        isLoading: isDoingTripDurationPrediction,
        isError: isErrorTripDurationPrediction,
        data: tripDurationPrediction
    } = useQuery( {
        queryKey: ['trip-duration-svr'],
        queryFn: () => getTripDurationPredictionSVR(input)
    })

    return {
        isLoadingTripDurationPredictionSVR: isDoingTripDurationPrediction,
        isErrorTripDurationPredictionSVR: isErrorTripDurationPrediction,
        tripDurationPredictionSVR: tripDurationPrediction
    }
}