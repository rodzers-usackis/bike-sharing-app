import {useMutation, useQueryClient} from "react-query";
import {updateMaintenanceVehicleDefectReportStatus} from "../../services/API";


export function useUpdateMaintenanceVehicleDefectsReportStatus() {
    const queryClient = useQueryClient()

    return useMutation({
        mutationFn: ({reportId, status}: {reportId:string, status:string}) => updateMaintenanceVehicleDefectReportStatus(reportId, status),
        onSuccess: () => {
            queryClient.invalidateQueries(['openVehicleDefectReports'])
        }
    })
}