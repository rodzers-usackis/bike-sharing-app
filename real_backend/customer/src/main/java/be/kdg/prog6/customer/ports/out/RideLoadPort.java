package be.kdg.prog6.customer.ports.out;

import be.kdg.prog6.customer.domain.Ride;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RideLoadPort {
    Optional<Ride> loadRide(UUID uuid);

    Optional<List<Ride>> loadAllRides(UUID customerUUID);
}
