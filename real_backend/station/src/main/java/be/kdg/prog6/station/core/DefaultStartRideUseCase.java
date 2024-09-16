package be.kdg.prog6.station.core;

import be.kdg.prog6.station.adapters.out.amqp.RideActivityPublisher;
import be.kdg.prog6.station.domain.Dock;
import be.kdg.prog6.station.domain.DockAction;
import be.kdg.prog6.station.domain.RideActivity;
import be.kdg.prog6.station.domain.Station;
import be.kdg.prog6.station.ports.in.StartRideCommand;
import be.kdg.prog6.station.ports.in.StartRideUseCase;
import be.kdg.prog6.station.ports.out.DockLoadPort;
import be.kdg.prog6.station.ports.out.DockUpdatePort;
import be.kdg.prog6.station.ports.out.RideActivityCreateDBPort;
import be.kdg.prog6.station.ports.out.StationLoadPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class DefaultStartRideUseCase implements StartRideUseCase {
    private final Logger logger = LoggerFactory.getLogger(DefaultStartRideUseCase.class);

    private final StationLoadPort stationLoadPort;
    private final DockUpdatePort dockUpdatePort;
    private final DockLoadPort dockLoadPort;

    private final RideActivityCreateDBPort rideActivityCreateDBPort;

    private RideActivityPublisher rideActivityPublisher;

    public DefaultStartRideUseCase(StationLoadPort stationLoadPort, DockUpdatePort dockUpdatePort, DockLoadPort dockLoadPort, RideActivityCreateDBPort rideActivityCreateDBPort, RideActivityPublisher rideActivityPublisher) {
        this.stationLoadPort = stationLoadPort;
        this.dockUpdatePort = dockUpdatePort;
        this.dockLoadPort = dockLoadPort;
        this.rideActivityCreateDBPort = rideActivityCreateDBPort;
        this.rideActivityPublisher = rideActivityPublisher;
    }

    @Override
    @Transactional
    public UUID startRide(StartRideCommand startRideCommand) {
        Optional<Station> optionalStation = stationLoadPort.loadStation(startRideCommand.uuid().uuid());

        Station station;
        if (optionalStation.isEmpty()){
            throw new RuntimeException(">>> Station not found");
        } else {
            station = optionalStation.get();
        }

        // Get random dock
        Random random = new Random();
        List<Dock> inUseDocks = station.getDocks().stream().filter(Dock::isDockInUse).toList();

        if (inUseDocks.size() == 0) {
            throw new RuntimeException(">>> No docks available at " + station.getName());
        }

        Dock randomDock = inUseDocks.get(random.nextInt(inUseDocks.size()));

        Optional<Dock> optionalDock = dockLoadPort.loadDock(randomDock.dockUUID().uuid());

        Dock dock;
        if (optionalDock.isEmpty()){
            throw new RuntimeException(">>> Dock not found");
        } else {
            dock = optionalDock.get();
        }

        RideActivity rideActivity = new RideActivity(UUID.randomUUID(), dock.getVehicleUUID(),
                startRideCommand.customerUUID(), DockAction.TAKE_OUT, startRideCommand.uuid().uuid(),
                randomDock.dockUUID().uuid(), LocalDateTime.now());

        rideActivityPublisher.createRideActivity(rideActivity.getVehicleUUID(), rideActivity.getCustomerUUID(), rideActivity.getDockAction(), rideActivity.getDockUUID());

        rideActivityCreateDBPort.createRideActivityDB(rideActivity);

        // Update dock state
        randomDock.takeBike();

        randomDock.setStationUUID(station.getUuid());

        randomDock.setVehicleUUID(null);

        // Save dock
        dockUpdatePort.updateDock(randomDock);

        logger.info("Started ride at dock " + randomDock.getDockNumber() + " at station " + station.getName() + " with bike " + dock.getVehicleUUID());

        return rideActivity.getVehicleUUID();
    }
}
