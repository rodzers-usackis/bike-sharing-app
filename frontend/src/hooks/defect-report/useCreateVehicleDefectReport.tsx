import {useMutation} from "react-query";
import {DefectFormInputs} from "../../components/pages/defect-report/DefectFormInputs";
import {createVehicleDefectReport} from "../../services/API";


export function useCreateVehicleDefectReport() {
    return useMutation({
        mutationFn: (vehicleDefectReportInputs : DefectFormInputs) => createVehicleDefectReport(vehicleDefectReportInputs),
    })
}