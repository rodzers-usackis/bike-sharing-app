package be.kdg.prog6.maintenance.ports.in;

import be.kdg.prog6.maintenance.domain.VehicleDefectReport;

public interface CreateVehicleDefectReportUseCase {
    VehicleDefectReport createVehicleDefectReport(CreateVehicleDefectReportCommand command);
}
