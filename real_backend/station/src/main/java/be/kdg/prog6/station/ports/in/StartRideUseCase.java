package be.kdg.prog6.station.ports.in;

import java.util.UUID;

public interface StartRideUseCase {
    UUID startRide(StartRideCommand startRideCommand);
}
