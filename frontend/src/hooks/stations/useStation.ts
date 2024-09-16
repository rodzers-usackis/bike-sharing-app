import {useQuery} from 'react-query'
import {getStation} from '../../services/API'

export function useStation(id: string) {

    const {
        isLoading: isDoingGetStation,
        isError: isErrorGetStation,
        data: station
    } = useQuery( {
        queryKey: ['station', id],
        queryFn: () => getStation(id)
    })

    return {
        isLoadingStation: isDoingGetStation,
        isErrorLoadingStation: isErrorGetStation,
        station
    }
}