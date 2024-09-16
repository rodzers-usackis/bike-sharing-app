package be.kdg.prog6.station.core;

import be.kdg.prog6.station.adapters.out.amqp.RideActivityPublisher;
import be.kdg.prog6.station.domain.*;
import be.kdg.prog6.station.ports.in.EndRideCommand;
import be.kdg.prog6.station.ports.in.EndRideUseCase;
import be.kdg.prog6.station.ports.out.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultEndRideUseCase implements EndRideUseCase {
    private final Logger logger = LoggerFactory.getLogger(DefaultEndRideUseCase.class);

    private final StationLoadPort stationLoadPort;
    private final DockUpdatePort dockUpdatePort;
    private final DockLoadPort dockLoadPort;

    private final RideCreatePort rideCreatePort;
    private final RideActivityCreateDBPort rideActivityCreateDBPort;

    private final RideActivityLoadPort rideActivityLoadPort;

    private RideActivityPublisher rideActivityPublisher;

    public DefaultEndRideUseCase(StationLoadPort stationLoadPort, DockUpdatePort dockUpdatePort, DockLoadPort dockLoadPort, RideCreatePort rideCreatePort, RideActivityCreateDBPort rideActivityCreateDBPort, RideActivityLoadPort rideActivityLoadPort, RideActivityPublisher rideActivityPublisher) {
        this.stationLoadPort = stationLoadPort;
        this.dockUpdatePort = dockUpdatePort;
        this.dockLoadPort = dockLoadPort;
        this.rideCreatePort = rideCreatePort;
        this.rideActivityCreateDBPort = rideActivityCreateDBPort;
        this.rideActivityLoadPort = rideActivityLoadPort;
        this.rideActivityPublisher = rideActivityPublisher;
    }

    @Override
    @Transactional
    public UUID endRide(EndRideCommand endRideCommand) {
        Optional<Station> optionalStation = stationLoadPort.loadStation(endRideCommand.uuid().uuid());

        Station station;
        if (optionalStation.isEmpty()){
            throw new RuntimeException(">>> Station not found");
        } else {
            station = optionalStation.get();
        }

        Dock dock = station.getDocks().stream().filter(d -> d.getDockNumber() == endRideCommand.dockNumber()).findFirst().orElseThrow();

        if (dock.getDockState() == DockState.IN_USE) {
            throw new RuntimeException(">>> Dock " + endRideCommand.dockNumber() + " not available");
        }

        Optional<RideActivity> startRideActivity = rideActivityLoadPort.loadTakeOutBikeRideActivityFromCustomerUUID(endRideCommand.customerUUID());

        if (startRideActivity.isEmpty()) {
            throw new RuntimeException(">>> No ride activity found for customer " + endRideCommand.customerUUID());
        }

        // Update dock state
        dock.parkBike();

        dock.setStationUUID(station.getUuid());

        dock.setVehicleUUID(startRideActivity.get().getVehicleUUID());

        // Save dock
        dockUpdatePort.updateDock(dock);

        logger.info("Ended a ride at dock " + dock.getDockNumber() + " at station " + station.getName());

        RideActivity endRideActivity = new RideActivity(startRideActivity.get().getRideUUID(), dock.getVehicleUUID(),
                endRideCommand.customerUUID(), DockAction.PUT_IN, endRideCommand.uuid().uuid(),
                dock.dockUUID().uuid(), LocalDateTime.now());

        rideActivityPublisher.createRideActivity(endRideActivity.getVehicleUUID(), endRideActivity.getCustomerUUID(), endRideActivity.getDockAction(), endRideActivity.getDockUUID());

        rideActivityCreateDBPort.createRideActivityDB(endRideActivity);

        Optional<Station> startStation = stationLoadPort.loadStation(startRideActivity.get().getStationUUID());

        if (startStation.isEmpty()) {
            throw new RuntimeException(">>> Start station not found");
        }

        Optional<Station> endStation = stationLoadPort.loadStation(endRideActivity.getStationUUID());

        if (endStation.isEmpty()) {
            throw new RuntimeException(">>> End station not found");
        }

        rideCreatePort.createRide(startRideActivity.get().getTime(), startStation.get().getName(), LocalDateTime.now(), endStation.get().getName(), startRideActivity.get().getRideUUID(), endRideActivity.getCustomerUUID());

        return startRideActivity.get().getRideUUID();
    }
}
