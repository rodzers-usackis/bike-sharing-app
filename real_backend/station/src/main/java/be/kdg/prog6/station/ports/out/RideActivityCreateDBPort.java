package be.kdg.prog6.station.ports.out;

import be.kdg.prog6.station.domain.RideActivity;

public interface RideActivityCreateDBPort {
    void createRideActivityDB(RideActivity rideActivity);
}
