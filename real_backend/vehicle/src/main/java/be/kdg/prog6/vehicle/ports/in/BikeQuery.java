package be.kdg.prog6.vehicle.ports.in;

import be.kdg.prog6.vehicle.domain.Bike;

import java.util.List;
import java.util.Optional;

public interface BikeQuery{
    Optional<List<Bike>> findAllBikes();
}
