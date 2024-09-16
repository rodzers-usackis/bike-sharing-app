import {useQuery} from "react-query";
import {getAllBikes} from "../../services/API";

export function useBikes() {

    const {
        isLoading: isDoingGetBikes,
        isError: isErrorGetBikes,
        data: bikes
    } = useQuery( {
        queryKey: ['bikes'],
        queryFn: () => getAllBikes()
    })

    return {
        isLoadingBikes: isDoingGetBikes,
        isErrorLoadingBikes: isErrorGetBikes,
        bikes
    }
}