package be.kdg.prog6.station.ports.in;

import be.kdg.prog6.station.domain.Station;

import java.util.UUID;

//TODO: Add parameters, for now we only use the UUID
public record StartRideCommand(Station.StationUUID uuid, UUID customerUUID) {

}
