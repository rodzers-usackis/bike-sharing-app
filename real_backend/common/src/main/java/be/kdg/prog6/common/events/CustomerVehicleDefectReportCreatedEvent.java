package be.kdg.prog6.common.events;

import java.io.Serializable;
import java.util.UUID;

public record CustomerVehicleDefectReportCreatedEvent (
        UUID vehicleDefectReportId,
        UUID customerId,
        UUID vehicleId,
        String description,
        boolean isUnusable,
        boolean hasBrokenWheel,
        boolean hasFlatTyre,
        boolean hasBrokenLight,
        boolean hasBrokenBrake,
        boolean hasOtherDefect,
        String photo
) implements Serializable{
}
