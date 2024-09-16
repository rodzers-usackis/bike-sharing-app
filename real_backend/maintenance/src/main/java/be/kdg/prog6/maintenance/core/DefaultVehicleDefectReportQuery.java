package be.kdg.prog6.maintenance.core;

import be.kdg.prog6.maintenance.domain.VehicleDefectReport;
import be.kdg.prog6.maintenance.ports.in.VehicleDefectReportQuery;
import be.kdg.prog6.maintenance.ports.out.VehicleDefectReportLoadPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultVehicleDefectReportQuery implements VehicleDefectReportQuery {

    private final VehicleDefectReportLoadPort vehicleDefectReportLoadPort;

    public DefaultVehicleDefectReportQuery(VehicleDefectReportLoadPort vehicleDefectReportLoadPort) {
        this.vehicleDefectReportLoadPort = vehicleDefectReportLoadPort;
    }


    @Override
    public List<VehicleDefectReport> getAllVehicleDefectReports() {
        return vehicleDefectReportLoadPort.loadAllVehicleDefectReports().orElseThrow();
    }

    @Override
    public List<VehicleDefectReport> getOpenVehicleDefectReports() {
        return vehicleDefectReportLoadPort.loadOpenVehicleDefectReports().orElseThrow();
    }
}
