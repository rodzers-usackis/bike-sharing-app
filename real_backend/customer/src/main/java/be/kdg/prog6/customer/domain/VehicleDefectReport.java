package be.kdg.prog6.customer.domain;

import java.time.LocalDateTime;
import java.util.*;

public class VehicleDefectReport {
    private UUID uuid;
    private LocalDateTime timestamp = LocalDateTime.now();
    private String description;
    private Customer customer;
    private UUID vehicleId;
    private boolean isUnusable;
    private boolean hasBrokenWheel;
    private boolean hasFlatTyre;
    private boolean hasBrokenLight;
    private boolean hasBrokenBrake;
    private boolean hasOtherDefect;
    private String photo;


    public VehicleDefectReport() {
    }

    public VehicleDefectReport(UUID uuid, LocalDateTime timestamp, String description, Customer customer, UUID vehicleId, boolean isUnusable, boolean hasBrokenWheel, boolean hasFlatTyre, boolean hasBrokenLight, boolean hasBrokenBrake, boolean hasOtherDefect, String photo) {
        this.uuid = uuid;
        this.timestamp = timestamp;
        this.description = description;
        this.customer = customer;
        this.vehicleId = vehicleId;
        this.isUnusable = isUnusable;
        this.hasBrokenWheel = hasBrokenWheel;
        this.hasFlatTyre = hasFlatTyre;
        this.hasBrokenLight = hasBrokenLight;
        this.hasBrokenBrake = hasBrokenBrake;
        this.hasOtherDefect = hasOtherDefect;
        this.photo = photo;
    }


    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public UUID getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(UUID vehicleId) {
        this.vehicleId = vehicleId;
    }

    public boolean isUnusable() {
        return isUnusable;
    }

    public void setUnusable(boolean unusable) {
        isUnusable = unusable;
    }

    public boolean isHasBrokenWheel() {
        return hasBrokenWheel;
    }

    public void setHasBrokenWheel(boolean hasBrokenWheel) {
        this.hasBrokenWheel = hasBrokenWheel;
    }

    public boolean isHasFlatTyre() {
        return hasFlatTyre;
    }

    public void setHasFlatTyre(boolean hasFlatTyre) {
        this.hasFlatTyre = hasFlatTyre;
    }

    public boolean isHasBrokenLight() {
        return hasBrokenLight;
    }

    public void setHasBrokenLight(boolean hasBrokenLight) {
        this.hasBrokenLight = hasBrokenLight;
    }

    public boolean isHasBrokenBrake() {
        return hasBrokenBrake;
    }

    public void setHasBrokenBrake(boolean hasBrokenBrake) {
        this.hasBrokenBrake = hasBrokenBrake;
    }

    public boolean isHasOtherDefect() {
        return hasOtherDefect;
    }

    public void setHasOtherDefect(boolean hasOtherDefect) {
        this.hasOtherDefect = hasOtherDefect;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}