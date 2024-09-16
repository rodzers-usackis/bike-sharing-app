package be.kdg.prog6.common.events;

import java.time.LocalDateTime;
import java.util.UUID;

public record RideEndedEvent(LocalDateTime startTime, String startStation, LocalDateTime endTime, String endStation, UUID rideUUID, UUID customerUUID) {
}
