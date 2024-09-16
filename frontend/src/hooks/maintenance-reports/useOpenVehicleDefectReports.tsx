import {useQuery} from "react-query";
import {getOpenMaintenanceVehicleDefectReports} from "../../services/API";
import {MaintenanceVehicleDefectReport} from "../../model/maintenance-reports/MaintenanceVehicleDefectReport";

export function useOpenVehicleDefectReports() {
    const {data, isError, isLoading} = useQuery({
        queryKey: ['openVehicleDefectReports'],
        queryFn: () => getOpenMaintenanceVehicleDefectReports(),
    })
    return {openReports:data, isOpenReportsError:isError, isOpenReportsLoading:isLoading}
}