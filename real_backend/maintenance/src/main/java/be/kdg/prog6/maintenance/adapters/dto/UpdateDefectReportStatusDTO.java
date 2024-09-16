package be.kdg.prog6.maintenance.adapters.dto;

import be.kdg.prog6.maintenance.domain.VehicleDefectReportStatus;

import javax.validation.constraints.NotNull;

public class UpdateDefectReportStatusDTO {
    @NotNull
    private VehicleDefectReportStatus status;

    public VehicleDefectReportStatus getStatus() {
        return status;
    }

    public void setStatus(VehicleDefectReportStatus status) {
        this.status = status;
    }
}
