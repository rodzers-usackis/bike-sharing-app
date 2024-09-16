package be.kdg.prog6.customer.ports.in;

import be.kdg.prog6.customer.domain.Ride;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RideQuery {
    Ride getRide(UUID rideUUID);

    List<Ride> getAllRides(UUID customerUUID);
}
