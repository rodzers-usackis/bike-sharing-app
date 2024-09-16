import {useQuery} from 'react-query'
import {getTripDurationPredictionNN} from '../../services/API'
import {AIModelPredictionInput} from "../../model/predictions/AIModelPredictionInput";

export function useTripDurationPredictionNN(input: AIModelPredictionInput) {

    const {
        isLoading: isDoingTripDurationPredictionNN,
        isError: isErrorTripDurationPredictionNN,
        data: tripDurationPredictionNN
    } = useQuery( {
        queryKey: ['trip-duration-nn'],
        queryFn: () => getTripDurationPredictionNN(input)
    })

    return {
        isLoadingTripDurationPredictionNN: isDoingTripDurationPredictionNN,
        isErrorTripDurationPredictionNN: isErrorTripDurationPredictionNN,
        tripDurationPredictionNN: tripDurationPredictionNN
    }
}