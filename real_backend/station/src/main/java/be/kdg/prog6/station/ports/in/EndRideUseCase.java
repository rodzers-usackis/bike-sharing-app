package be.kdg.prog6.station.ports.in;

import java.util.UUID;

public interface EndRideUseCase {
    UUID endRide(EndRideCommand endRideCommand);
}
