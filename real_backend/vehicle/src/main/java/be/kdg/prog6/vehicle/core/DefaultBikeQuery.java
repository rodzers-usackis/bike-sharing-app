package be.kdg.prog6.vehicle.core;

import be.kdg.prog6.vehicle.domain.Bike;
import be.kdg.prog6.vehicle.ports.in.BikeQuery;
import be.kdg.prog6.vehicle.ports.out.BikeLoadPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultBikeQuery implements BikeQuery {

    private final BikeLoadPort bikeLoadPort;

    public DefaultBikeQuery(BikeLoadPort bikeLoadPort) {
        this.bikeLoadPort = bikeLoadPort;
    }

    @Override
    public Optional<List<Bike>> findAllBikes() {
        // TODO: Testing output, remove later

        // Try to load all bikes
        // If no bikes are found, return an empty optional
        try {
            return bikeLoadPort.loadAllBikes();
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
