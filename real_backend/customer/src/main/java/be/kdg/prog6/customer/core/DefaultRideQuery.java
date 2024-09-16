package be.kdg.prog6.customer.core;

import be.kdg.prog6.customer.domain.Ride;
import be.kdg.prog6.customer.ports.in.RideQuery;
import be.kdg.prog6.customer.ports.out.RideLoadPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DefaultRideQuery implements RideQuery {
    private final RideLoadPort rideLoadPort;

    public DefaultRideQuery(RideLoadPort rideLoadPort) {
        this.rideLoadPort = rideLoadPort;
    }

    @Override
    public Ride getRide(UUID rideUUID) {
        return rideLoadPort.loadRide(rideUUID).orElse(null);
    }

    @Override
    public List<Ride> getAllRides(UUID customerUUID) {
        return rideLoadPort.loadAllRides(customerUUID).orElse(null);
    }
}
