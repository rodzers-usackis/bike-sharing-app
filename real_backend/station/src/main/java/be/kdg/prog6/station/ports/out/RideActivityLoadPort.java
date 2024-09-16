package be.kdg.prog6.station.ports.out;

import be.kdg.prog6.station.domain.RideActivity;

import java.util.Optional;
import java.util.UUID;

public interface RideActivityLoadPort {
    Optional<RideActivity> loadRideActivity(UUID uuid);

    Optional<RideActivity> loadTakeOutBikeRideActivityFromCustomerUUID(UUID customerUUID);
}
