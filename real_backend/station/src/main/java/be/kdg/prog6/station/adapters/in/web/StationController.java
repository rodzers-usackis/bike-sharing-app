package be.kdg.prog6.station.adapters.in.web;

import be.kdg.prog6.station.domain.DockState;
import be.kdg.prog6.station.domain.Station;
import be.kdg.prog6.station.ports.in.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class StationController {
    private final Logger logger = LoggerFactory.getLogger(StationController.class);
    private final CreateStationUseCase createStationUseCase;

    private final StartRideUseCase startRideUseCase;
    private final EndRideUseCase endRideUseCase;

    private final StationDocksQuery stationDocksQuery;
    private final StationQuery stationQuery;

    public StationController(CreateStationUseCase createStationUseCase, StartRideUseCase startRideUseCase, EndRideUseCase endRideUseCase, StationDocksQuery stationDocksQuery, StationQuery stationQuery) {
        this.createStationUseCase = createStationUseCase;
        this.startRideUseCase = startRideUseCase;
        this.endRideUseCase = endRideUseCase;
        this.stationDocksQuery = stationDocksQuery;
        this.stationQuery = stationQuery;
    }

    @PostMapping("/api/stations/create")
    public void createVehicle(){
    }

    @CrossOrigin
    @PostMapping("/api/stations/ride/start/{customerUUID}/{stationUUID}")
    public UUID startRide(@PathVariable String stationUUID, @PathVariable String customerUUID){
        logger.info("Trying to start a ride at station " + stationUUID);
        // Try to convert the string to a UUID
        UUID uuid;
        try {
            uuid = UUID.fromString(stationUUID);
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid UUID string: " + stationUUID);
            return null;
        }

        UUID covertedCustomerUUID;
        try {
            covertedCustomerUUID = UUID.fromString(customerUUID);
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid UUID string: " + customerUUID);
            return null;
        }

        return startRideUseCase.startRide(new StartRideCommand(new Station.StationUUID(uuid), covertedCustomerUUID));
    }

    @CrossOrigin
    @PostMapping("/api/stations/ride/end/{customerUUID}/{stationUUID}/{dockNumber}")
    public UUID endRide(@PathVariable String stationUUID, @PathVariable int dockNumber, @PathVariable String customerUUID){
        logger.info("Trying to end a ride at station " + stationUUID);
        // Try to convert the string to a UUID
        UUID stationUUIDConverted;
        try {
            stationUUIDConverted = UUID.fromString(stationUUID);
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid UUID string: " + stationUUID);
            return null;
        }

        UUID customerUUIDConverted;
        try {
            customerUUIDConverted = UUID.fromString(customerUUID);
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid UUID string: " + customerUUID);
            return null;
        }

        return endRideUseCase.endRide(new EndRideCommand(new Station.StationUUID(stationUUIDConverted), dockNumber, customerUUIDConverted));
    }

    @GetMapping("/api/stations/{stationId}/docks/{dockState}")
    public int getDockAmountByAvailability(@PathVariable UUID stationId, @PathVariable String dockState){
        logger.info("Trying to get " + dockState + " docks at station " + stationId);
        if (dockState.toUpperCase().equals(DockState.AVAILABLE.name())) {
            return stationDocksQuery.getNumberOfDocksAvailable(new StationDocksCommand(new Station.StationUUID(stationId).uuid(), DockState.AVAILABLE));
        } else if (dockState.toUpperCase().equals(DockState.IN_USE.name())) {
            return stationDocksQuery.getNumberOfDocksInUse(new StationDocksCommand(new Station.StationUUID(stationId).uuid(), DockState.IN_USE));
        } else {
            return 0;
        }
    }

    // TODO: disable CORS in config?
    @CrossOrigin
    @GetMapping("/api/stations")
    public List<Station> getAllStations(){
        return stationQuery.findAllStations();
    }
}
