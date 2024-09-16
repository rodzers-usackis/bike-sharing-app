package be.kdg.prog6.station.domain;
import java.util.UUID;

public class Dock {
    private DockUUID dockUUID;

    private Station.StationUUID stationUUID;

    private UUID vehicleUUID;

    private int dockNumber;

    private DockState dockState;

    public record DockUUID(UUID uuid) {
    }

    public Dock(DockUUID dockUUID, int dockNumber, DockState dockState) {
        this.dockUUID = dockUUID;
        this.dockNumber = dockNumber;
        this.dockState = dockState;
    }

    public DockUUID dockUUID() {
        return dockUUID;
    }

    public void parkBike() {
        this.dockState = DockState.IN_USE;
    }

    public void takeBike() {
        this.dockState = DockState.AVAILABLE;
    }

    public boolean isDockInUse() {
        return dockState == DockState.IN_USE;
    }

    public boolean isDockAvailable() {
        return dockState == DockState.AVAILABLE;
    }

    public int getDockNumber() {
        return dockNumber;
    }

    public DockState getDockState() {
        return dockState;
    }

    public void setStationUUID(Station.StationUUID stationUUID) {
        this.stationUUID = stationUUID;
    }

    public Station.StationUUID getStationUUID() {
        return stationUUID;
    }

    public void setDockNumber(int dockNumber) {
        this.dockNumber = dockNumber;
    }

    public void setVehicleUUID(UUID vehicleUUID) {
        this.vehicleUUID = vehicleUUID;
    }

    public UUID getVehicleUUID() {
        return vehicleUUID;
    }

}