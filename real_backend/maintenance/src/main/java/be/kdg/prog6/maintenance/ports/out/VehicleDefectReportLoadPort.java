package be.kdg.prog6.maintenance.ports.out;

import be.kdg.prog6.maintenance.domain.VehicleDefectReport;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VehicleDefectReportLoadPort {
    Optional<VehicleDefectReport> loadVehicleDefectReport(UUID uuid);
    Optional<List<VehicleDefectReport>> loadAllVehicleDefectReports();
    Optional<List<VehicleDefectReport>>  loadOpenVehicleDefectReports();
}
