import {AIModelPredictionInput} from "../../model/predictions/AIModelPredictionInput";
import {useQuery} from "react-query";
import {getTripCountPrediction} from "../../services/API";

export function useTripCountPrediction(input: AIModelPredictionInput) {

    const {
        isLoading: isDoingTripCountPrediction,
        isError: isErrorTripCountPrediction,
        data: tripCountPrediction
    } = useQuery( {
        queryKey: ['trip-count'],
        queryFn: () => getTripCountPrediction(input)
    })

    return {
        isLoadingTripCountPrediction: isDoingTripCountPrediction,
        isErrorTripCountPrediction: isErrorTripCountPrediction,
        tripCountPrediction
    }
}