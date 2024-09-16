package be.kdg.prog6.station.ports.in;

import be.kdg.prog6.station.domain.Station;

import java.util.List;

public interface StationQuery {
    List<Station> findAllStations();
}
