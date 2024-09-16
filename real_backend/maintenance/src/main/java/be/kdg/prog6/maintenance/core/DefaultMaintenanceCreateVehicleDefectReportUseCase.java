package be.kdg.prog6.maintenance.core;

import be.kdg.prog6.maintenance.adapters.mapper.DefectReportMapper;
import be.kdg.prog6.maintenance.domain.VehicleDefectReport;
import be.kdg.prog6.maintenance.domain.VehicleDefectReportStatus;
import be.kdg.prog6.maintenance.ports.in.CreateVehicleDefectReportCommand;
import be.kdg.prog6.maintenance.ports.in.CreateVehicleDefectReportUseCase;
import be.kdg.prog6.maintenance.ports.out.VehicleDefectReportCreatePort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class DefaultMaintenanceCreateVehicleDefectReportUseCase implements CreateVehicleDefectReportUseCase {

    private final VehicleDefectReportCreatePort vehicleDefectReportCreatePort;

    public DefaultMaintenanceCreateVehicleDefectReportUseCase(VehicleDefectReportCreatePort vehicleDefectReportCreatePort) {
        this.vehicleDefectReportCreatePort = vehicleDefectReportCreatePort;
    }

    @Override
    public VehicleDefectReport createVehicleDefectReport(CreateVehicleDefectReportCommand command) {
        VehicleDefectReport vehicleDefectReport = DefectReportMapper.INSTANCE.mapToDomain(command);
        vehicleDefectReport.setUuid(UUID.randomUUID());
        vehicleDefectReport.setStatus(VehicleDefectReportStatus.OPEN);
        vehicleDefectReport.setTimestamp(LocalDateTime.now());
        return vehicleDefectReportCreatePort.createVehicleDefectReport(vehicleDefectReport);
    }
}
