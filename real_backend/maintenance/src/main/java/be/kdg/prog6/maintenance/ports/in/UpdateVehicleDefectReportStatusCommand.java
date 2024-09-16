package be.kdg.prog6.maintenance.ports.in;

import be.kdg.prog6.maintenance.domain.VehicleDefectReportStatus;

import java.util.UUID;

public record UpdateVehicleDefectReportStatusCommand(UUID uuid, VehicleDefectReportStatus status) {

}
