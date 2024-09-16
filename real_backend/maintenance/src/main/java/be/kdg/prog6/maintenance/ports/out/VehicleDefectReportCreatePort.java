package be.kdg.prog6.maintenance.ports.out;

import be.kdg.prog6.maintenance.domain.VehicleDefectReport;

public interface VehicleDefectReportCreatePort {
    VehicleDefectReport createVehicleDefectReport(VehicleDefectReport vehicleDefectReport);
}
