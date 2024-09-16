package be.kdg.prog6.station.ports.out;

import be.kdg.prog6.station.domain.DockAction;

import java.util.UUID;

public interface RideActivityCreatePort {
    void createRideActivity(UUID vehicleUUID, UUID CustomerUUID, DockAction action, UUID DockUUID);
}
