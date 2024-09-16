package be.kdg.prog6.maintenance.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class VehicleDefectReport {
    private UUID uuid;
    private UUID customerContextReportId;
    private String description;
    private UUID vehicleId;
    private UUID customerId;
    private LocalDateTime timestamp;
    private boolean isUnusable;
    private boolean hasBrokenWheel;
    private boolean hasFlatTyre;
    private boolean hasBrokenLight;
    private boolean hasBrokenBrake;
    private boolean hasOtherDefect;
    private String photo;
    private VehicleDefectReportStatus status;


    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getCustomerContextReportId() {
        return customerContextReportId;
    }

    public void setCustomerContextReportId(UUID customerContextReportId) {
        this.customerContextReportId = customerContextReportId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(UUID vehicleId) {
        this.vehicleId = vehicleId;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
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

    public VehicleDefectReportStatus getStatus() {
        return status;
    }

    public void setStatus(VehicleDefectReportStatus status) {
        this.status = status;
    }
}
