package be.kdg.prog6.station.ports.in;

import be.kdg.prog6.station.domain.Address;
import be.kdg.prog6.station.domain.Location;
import be.kdg.prog6.station.domain.Station;

public record CreateStationCommand(Station.StationUUID uuid, Address address, Location location, String name) {
}
