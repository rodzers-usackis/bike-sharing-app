import {useQuery} from "react-query";
import {startBikeRide} from "../../services/API";


export function useStartRide(uuid: string) {
    const {
        isLoading: isDoingStartRide,
        isError: isErrorStartRide,
    } = useQuery( {
        queryKey: ['start-ride'],
        queryFn: () => startBikeRide(uuid)
    })

    return {
        isLoadingStartRide: isDoingStartRide,
        isErrorStartRide: isErrorStartRide,
    }
}