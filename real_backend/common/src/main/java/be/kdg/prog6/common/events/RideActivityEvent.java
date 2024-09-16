package be.kdg.prog6.common.events;

import java.util.UUID;

public record RideActivityEvent(UUID vehicleUUID, UUID customerUUID, String action, UUID dockUUID) {
    public static final String ROUTING_KEY = "RideActivity";
}
