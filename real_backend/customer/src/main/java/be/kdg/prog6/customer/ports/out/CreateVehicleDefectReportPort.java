package be.kdg.prog6.customer.ports.out;

import be.kdg.prog6.customer.domain.VehicleDefectReport;

public interface CreateVehicleDefectReportPort {
    VehicleDefectReport createVehicleDefectReport(VehicleDefectReport vehicleDefectReport);
}
