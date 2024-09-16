package be.kdg.prog6.customer.ports.out;

import be.kdg.prog6.customer.domain.Ride;

public interface RideCreatePort {
    void createRide(Ride ride);
}
