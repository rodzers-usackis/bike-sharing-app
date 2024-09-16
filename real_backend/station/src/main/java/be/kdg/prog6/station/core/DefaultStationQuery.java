package be.kdg.prog6.station.core;

import be.kdg.prog6.station.domain.Station;
import be.kdg.prog6.station.ports.in.StationQuery;
import be.kdg.prog6.station.ports.out.StationLoadPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultStationQuery implements StationQuery {
    private final StationLoadPort stationLoadPort;

    public DefaultStationQuery(StationLoadPort stationLoadPort) {
        this.stationLoadPort = stationLoadPort;
    }


    @Override
    public List<Station> findAllStations() {
        return stationLoadPort.loadAllStations();
    }
}
