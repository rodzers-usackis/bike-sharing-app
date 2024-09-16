package be.kdg.prog6.customer.ports.in;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateRideCommand(LocalDateTime startTime, String startStation, LocalDateTime endTime, String endStation, UUID rideUUID, UUID customerUUID) {
}
