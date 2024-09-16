import {useQuery} from "react-query";
import {getAvailableDocks} from "../../services/API";

export function useAvailableStationDocks(stationId: string, status: string) {

    const {
        isLoading: isLoadingGetAvailableDocks,
        isError: isErrorGetAvailableDocks,
        data: docks
    } = useQuery( {
        queryKey: ['docks'],
        queryFn: () => getAvailableDocks(stationId, status)
    })

    return {
        isLoadingGetAvailableDocks: isLoadingGetAvailableDocks,
        isErrorGetAvailableDocks: isErrorGetAvailableDocks,
        docks
    }
}