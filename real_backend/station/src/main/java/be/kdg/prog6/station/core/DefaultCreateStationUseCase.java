package be.kdg.prog6.station.core;

import be.kdg.prog6.station.domain.Station;
import be.kdg.prog6.station.ports.in.CreateStationCommand;
import be.kdg.prog6.station.ports.in.CreateStationUseCase;
import be.kdg.prog6.station.ports.out.StationCreatePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
public class DefaultCreateStationUseCase implements CreateStationUseCase {
    private final Logger logger = LoggerFactory.getLogger(DefaultCreateStationUseCase.class);
    private final StationCreatePort stationCreatePort;

    public DefaultCreateStationUseCase(StationCreatePort stationCreatePort) {
        this.stationCreatePort = stationCreatePort;
    }

    @Override
    @Transactional
    public void createStation(CreateStationCommand createStationCommand) {
        logger.info("Creating Station with uuid: " + createStationCommand.uuid() + " and address: " + createStationCommand.address() + " and location: " + createStationCommand.location());

        Station station = new Station(createStationCommand.name(), createStationCommand.location(), createStationCommand.address(), Collections.emptyList());
        stationCreatePort.createStation(station);
    }
}
