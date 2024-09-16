package be.kdg.prog6.station.ports.in;

import be.kdg.prog6.station.domain.Station;

import java.util.UUID;

public record EndRideCommand(Station.StationUUID uuid, int dockNumber, UUID customerUUID) {
}
