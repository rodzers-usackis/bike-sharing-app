package be.kdg.prog6.vehicle.ports.out;

import be.kdg.prog6.vehicle.domain.Bike;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BikeLoadPort {
    Optional<Bike> loadBike(UUID uuid);
    Optional<List<Bike>> loadAllBikes();
}
