package be.kdg.prog6.customer.ports.in;

import be.kdg.prog6.customer.domain.VehicleDefectReport;

public interface CreateVehicleDefectReportUseCase {
    VehicleDefectReport createVehicleDefectReport(CreateVehicleDefectReportCommand command);
}
