import {useQuery} from 'react-query'
import {getStations} from '../../services/API'

export function useStations() {

    const {
        isLoading: isDoingGetStations,
        isError: isErrorGetStations,
        data: stations
    } = useQuery( {
        queryKey: ['stations'],
        queryFn: () => getStations()
    })

    return {
        isLoadingStations: isDoingGetStations,
        isErrorLoadingStations: isErrorGetStations,
        stations
    }
}