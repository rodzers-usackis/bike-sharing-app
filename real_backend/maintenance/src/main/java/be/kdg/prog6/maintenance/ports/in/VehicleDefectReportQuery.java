package be.kdg.prog6.maintenance.ports.in;

import be.kdg.prog6.maintenance.domain.VehicleDefectReport;

import java.util.List;
import java.util.UUID;

public interface VehicleDefectReportQuery {
    List<VehicleDefectReport> getAllVehicleDefectReports();
    List<VehicleDefectReport> getOpenVehicleDefectReports();
}
