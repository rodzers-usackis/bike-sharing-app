package be.kdg.prog6.station.core;

import be.kdg.prog6.station.domain.Dock;
import be.kdg.prog6.station.domain.Station;
import be.kdg.prog6.station.ports.in.StationDocksCommand;
import be.kdg.prog6.station.ports.in.StationDocksQuery;
import be.kdg.prog6.station.ports.out.StationLoadPort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultStationDocksQuery implements StationDocksQuery {

    private final StationLoadPort stationLoadPort;

    public DefaultStationDocksQuery(StationLoadPort stationLoadPort) {
        this.stationLoadPort = stationLoadPort;
    }

    @Override
    public int getNumberOfDocksAvailable(StationDocksCommand stationDocksCommand) {
        Optional<Station> station = stationLoadPort.loadStation(stationDocksCommand.station());
        return station.map(Station::numberOfAvailableDocks).orElse(0);
    }

    @Override
    public int getNumberOfDocksInUse(StationDocksCommand stationDocksCommand) {
        Optional<Station> station = stationLoadPort.loadStation(stationDocksCommand.station());
        return station.map(Station::numberOfInUseDocks).orElse(0);

    }
}
