package be.kdg.prog6.maintenance.adapters.dto;

import be.kdg.prog6.maintenance.domain.VehicleDefectReportStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record DefectReportDTO(
        UUID uuid,
        String description,
        String vehicleId,
        String customerId,
        LocalDateTime timestamp,
        boolean isUnusable,
        boolean hasBrokenWheel,
        boolean hasFlatTyre,
        boolean hasBrokenLight,
        boolean hasBrokenBrake,
        boolean hasOtherDefect,
        VehicleDefectReportStatus status,
        String photo
) {
}
