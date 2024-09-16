package be.kdg.prog6.station.ports.out;

import java.time.LocalDateTime;
import java.util.UUID;

public interface RideCreatePort {
    void createRide(LocalDateTime startTime, String startStation, LocalDateTime endTime, String endStation, UUID rideUUID, UUID customerUUID);
}
