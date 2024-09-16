import {useQuery} from 'react-query'
import {getAllRidesFromCustomer} from '../../services/API'

export function useRides(customerUUID: string) {

    const {
        isLoading: isDoingGetRides,
        isError: isErrorGetRides,
        data: rides
    } = useQuery( {
        queryKey: ['rides'],
        queryFn: () => getAllRidesFromCustomer(customerUUID)
    })

    return {
        isLoadingRides: isDoingGetRides,
        isErrorLoadingRides: isErrorGetRides,
        rides
    }
}