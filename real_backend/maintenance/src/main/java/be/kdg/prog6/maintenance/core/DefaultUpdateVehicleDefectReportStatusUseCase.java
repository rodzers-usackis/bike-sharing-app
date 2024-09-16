package be.kdg.prog6.maintenance.core;

import be.kdg.prog6.maintenance.domain.VehicleDefectReport;
import be.kdg.prog6.maintenance.ports.in.UpdateVehicleDefectReportStatusCommand;
import be.kdg.prog6.maintenance.ports.in.UpdateVehicleReportStatusUseCase;
import be.kdg.prog6.maintenance.ports.out.VehicleDefectReportLoadPort;
import be.kdg.prog6.maintenance.ports.out.VehicleDefectReportUpdatePort;
import org.springframework.stereotype.Service;

@Service
public class DefaultUpdateVehicleDefectReportStatusUseCase implements UpdateVehicleReportStatusUseCase {

    private final VehicleDefectReportUpdatePort vehicleDefectReportUpdatePort;
    private final VehicleDefectReportLoadPort vehicleDefectReportLoadPort;

    public DefaultUpdateVehicleDefectReportStatusUseCase(VehicleDefectReportUpdatePort vehicleDefectReportUpdatePort, VehicleDefectReportLoadPort vehicleDefectReportLoadPort) {
        this.vehicleDefectReportUpdatePort = vehicleDefectReportUpdatePort;
        this.vehicleDefectReportLoadPort = vehicleDefectReportLoadPort;
    }

    @Override
    public void updateVehicleReportStatus(UpdateVehicleDefectReportStatusCommand command) {
        VehicleDefectReport vehicleDefectReport = vehicleDefectReportLoadPort.loadVehicleDefectReport(command.uuid()).orElseThrow();
        vehicleDefectReport.setStatus(command.status());
        vehicleDefectReportUpdatePort.updateVehicleDefectReport(vehicleDefectReport);
    }
}
