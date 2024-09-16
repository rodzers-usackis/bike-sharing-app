package be.kdg.prog6.station.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Station {
    private StationUUID uuid;
    private String name;
    private Location location;
    private Address address;
    private List<Dock> docks = new ArrayList<>();

    public Station(String name, Location location, Address address, List<Dock> docks) {
        this.name = name;
        this.location = location;
        this.address = address;
        this.docks = docks;
    }

    public Station(StationUUID uuid) {
        this.uuid = uuid;
    }

    public record StationUUID(UUID uuid) {
    }

    public void addDockToStation(Dock dock) {
        docks.add(dock);
    }

    public int numberOfAvailableDocks() {
        return (int) docks.stream().filter(Dock::isDockAvailable).count();
    }

    public int numberOfInUseDocks() {
        return (int) docks.stream().filter(Dock::isDockInUse).count();
    }
}
