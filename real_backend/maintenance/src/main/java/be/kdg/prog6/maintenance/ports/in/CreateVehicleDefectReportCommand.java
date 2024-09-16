package be.kdg.prog6.maintenance.ports.in;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateVehicleDefectReportCommand(String description,
                                               UUID customerContextReportId,
                                               UUID vehicleId,
                                               UUID customerId,
                                               LocalDateTime timestamp,
                                               boolean isUnusable,
                                               boolean hasBrokenWheel,
                                               boolean hasFlatTyre,
                                               boolean hasBrokenLight,
                                               boolean hasBrokenBrake,
                                               boolean hasOtherDefect,
                                               String photo) {
}
