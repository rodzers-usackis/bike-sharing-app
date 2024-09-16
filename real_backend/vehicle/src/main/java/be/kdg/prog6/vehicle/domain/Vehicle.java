package be.kdg.prog6.vehicle.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Vehicle {
    private VehicleUUID id = new VehicleUUID(UUID.randomUUID());
    private LocalDateTime startDate;
    private LocalDateTime lastMaintenanceDate;
    //TODO: Fix Location
//    private Location location;
    private String model;
    private int distanceTraveledInMeters;
    private VehicleState vehicleState;

    public Vehicle(VehicleUUID id) {
        this.id = id;
    }

    public Vehicle(LocalDateTime startDate, LocalDateTime lastMaintenanceDate/*, Location location*/, String model, int distanceTraveledInMeters) {
        this.startDate = startDate;
        this.lastMaintenanceDate = lastMaintenanceDate;
//        this.location = location;
        this.model = model;
        this.distanceTraveledInMeters = distanceTraveledInMeters;
    }

    public record VehicleUUID(UUID uuid) {
    }

    public Vehicle() {

    }

    public VehicleUUID getId() {
        return id;
    }

    public void setId(VehicleUUID id) {
        this.id = id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getLastMaintenanceDate() {
        return lastMaintenanceDate;
    }

    public void setLastMaintenanceDate(LocalDateTime lastMaintenanceDate) {
        this.lastMaintenanceDate = lastMaintenanceDate;
    }

//    public Location getLocation() {
//        return location;
//    }
//
//    public void setLocation(Location location) {
//        this.location = location;
//    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getDistanceTraveledInMeters() {
        return distanceTraveledInMeters;
    }

    public void setDistanceTraveledInMeters(int distanceTraveledInMeters) {
        this.distanceTraveledInMeters = distanceTraveledInMeters;
    }

    public VehicleState getVehicleState() {
        return vehicleState;
    }

    public void setVehicleState(VehicleState vehicleState) {
        this.vehicleState = vehicleState;
    }
}
