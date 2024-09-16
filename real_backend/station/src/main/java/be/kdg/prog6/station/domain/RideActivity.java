package be.kdg.prog6.station.domain;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class RideActivity {

    private UUID rideActivityUUID;

    private UUID rideUUID;

    private UUID vehicleUUID;

    private UUID customerUUID;

    private DockAction dockAction;

    private UUID stationUUID;

    private UUID dockUUID;

    private LocalDateTime time;

    public RideActivity() {
    }

    public RideActivity(UUID rideUUID, UUID vehicleUUID, UUID customerUUID, DockAction dockAction, UUID stationUUID, UUID dockUUID, LocalDateTime time) {
        this.rideActivityUUID = UUID.randomUUID();
        this.rideUUID = rideUUID;
        this.vehicleUUID = vehicleUUID;
        this.customerUUID = customerUUID;
        this.dockAction = dockAction;
        this.stationUUID = stationUUID;
        this.dockUUID = dockUUID;
        this.time = time;
    }

    public RideActivity(UUID rideActivityUUID, UUID rideUUID, UUID vehicleUUID, UUID customerUUID, DockAction dockAction, UUID stationUUID, UUID dockUUID, LocalDateTime time) {
        this.rideActivityUUID = rideActivityUUID;
        this.rideUUID = rideUUID;
        this.vehicleUUID = vehicleUUID;
        this.customerUUID = customerUUID;
        this.dockAction = dockAction;
        this.stationUUID = stationUUID;
        this.dockUUID = dockUUID;
        this.time = time;
    }

    @Override
    public String toString() {
        return "RideActivity{" +
                "rideActivityUUID=" + rideActivityUUID +
                ", rideUUID=" + rideUUID +
                ", vehicleUUID=" + vehicleUUID +
                ", customerUUID=" + customerUUID +
                ", dockAction=" + dockAction +
                ", stationUUID=" + stationUUID +
                ", dockUUID=" + dockUUID +
                ", time=" + time +
                '}';
    }
}
