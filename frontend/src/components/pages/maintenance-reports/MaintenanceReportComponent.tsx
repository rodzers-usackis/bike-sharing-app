import {MaintenanceVehicleDefectReport} from "../../../model/maintenance-reports/MaintenanceVehicleDefectReport";
import {Box, Paper} from "@mui/material";
import './maintenance.css'

export function MaintenanceReportComponent(props: {
                                               report: MaintenanceVehicleDefectReport,
                                               selectedReportId: string | undefined,
                                                onReportClick: (id: string) => void
                                           },
) {

    const report = props.report;


    return (
        <Paper className={props.selectedReportId === report.uuid ? 'selected-report' : ''} sx={{display: 'flex', flexWrap: 'wrap', justifyContent: "flex-start", alignItems: 'center', padding: '10px', margin: '10px', cursor: 'pointer'}}
               onClick={() => props.onReportClick(report.uuid)}>
            <Box component="div"><span>{report.timestamp.toString()}</span></Box>
            <Box component="div">{(!!report.photo && <img alt="bike with the defects" src={report.photo}/>) || <span>No photo</span>}</Box>
            <Box component="div"><span><strong>Problem description: </strong>{report.description}</span></Box>
            <Box component="div"><span><strong>Defects: </strong>{getDefects(report)}</span></Box>
            <Box component="div"><span><strong>Status: </strong>{report.status}</span></Box>
        </Paper>
    )
}

function getDefects(report: MaintenanceVehicleDefectReport) {
    let defects = '';
    if(report.isUnusable){
        defects += 'Unusable, ';
    }
    if(report.hasFlatTyre){
        defects += 'Flat tire, ';
    }
    if(report.hasBrokenLight){
        defects += 'Broken light, ';
    }
    if(report.hasOtherDefect){
        defects += 'Other defect, ';
    }
    if(report.hasBrokenBrake){
        defects += 'Broken brake, ';

    }
    if(report.hasBrokenWheel){
        defects += 'Broken wheel, ';
    }

    return defects;

}

