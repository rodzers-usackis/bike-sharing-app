package be.kdg.prog6.customer.ports.in;

import be.kdg.prog6.customer.domain.VehicleDefect;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public record CreateVehicleDefectReportCommand(String description,
                                               UUID customerId,
                                               UUID vehicleId,
                                               boolean isUnusable,
                                               boolean hasBrokenWheel,
                                               boolean hasFlatTyre,
                                               boolean hasBrokenLight,
                                               boolean hasBrokenBrake,
                                               boolean hasOtherDefect,
                                               String photo) {
}



