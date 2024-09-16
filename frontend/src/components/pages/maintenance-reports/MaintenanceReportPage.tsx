import {useOpenVehicleDefectReports} from "../../../hooks/maintenance-reports/useOpenVehicleDefectReports";
import {Alert, Button, CircularProgress, Drawer, MenuItem, Select, Typography} from "@mui/material";
import React, {useState} from "react";
import {MaintenanceReportComponent} from "./MaintenanceReportComponent";
import {useUpdateMaintenanceVehicleDefectsReportStatus} from "../../../hooks/maintenance-reports/useUpdateMaintenanceVehicleDefectsReportStatus";
import './maintenance.css'


export function MaintenanceReportPage() {
    const [drawerState, setDrawerState] = useState(false);
    const [newStatus, setNewStatus] = useState<string>("OPEN");
    const [selectedReportId, setSelectedReportId] = useState<string>();
    const {openReports, isOpenReportsError, isOpenReportsLoading} = useOpenVehicleDefectReports()
    const {mutate: mutateReportStatus} = useUpdateMaintenanceVehicleDefectsReportStatus()

    if (!openReports || isOpenReportsLoading) {
        return <CircularProgress sx={{display: "block", mt: "10em", mx: "auto"}}/>
    }

    if (isOpenReportsError) {
        return <Alert severity="error">Reports could not be loaded</Alert>;
    }

    function onReportClick(id: string) {
        setSelectedReportId(id);
        setDrawerState(true);
    }

    return (
        <><Drawer style={{padding: "10px"}} onClose={() => setDrawerState(false)} open={drawerState} anchor="right">
            <Typography sx={{marginBottom: 5, wordWrap: "break-word"}} variant="h5">Update this report's
                status</Typography>

            <form>
                <Select value={newStatus} onChange={(event) => {
                    if (!!event.target.value) {
                        setNewStatus(event.target.value as string)
                    }
                }}>
                    <MenuItem value={"OPEN"}>Open</MenuItem>
                    <MenuItem value={"IN_PROGRESS"}>In progress</MenuItem>
                    <MenuItem value={"SOLVED"}>Solved</MenuItem>
                    <MenuItem value={"CANCELLED"}>Cancelled</MenuItem>
                </Select>
                <Button type="submit" onClick={() => {
                    if (!!newStatus && !!selectedReportId) {
                        mutateReportStatus({reportId: selectedReportId, status: newStatus})
                        setDrawerState(false)
                    }
                }}>Update</Button>
            </form>

        </Drawer>
            {!!openReports ? openReports.map((report) => <MaintenanceReportComponent key={report.uuid} onReportClick={onReportClick}
                                                                     report={report}
                                                                     selectedReportId={selectedReportId}
            />) : <Alert severity="error">No reports found</Alert>}
        </>
    )
}

