package be.kdg.prog6.station.ports.out;

import be.kdg.prog6.station.domain.Station;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StationLoadPort {
    Optional<Station> loadStation(UUID uuid);
    List<Station> loadAllStations();
}
