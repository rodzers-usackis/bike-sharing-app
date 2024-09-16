package be.kdg.prog6.customer.domain;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Ride {
    private UUID uuid;
    private UUID customerUUID;
    private LocalDateTime startTime;
    private String startStation;
    private LocalDateTime endTime;
    private String endStation;
    private float totalCost;
    private float activationFee;
    private float pricePerMinute;

    public Ride(UUID uuid, UUID customerUUID, LocalDateTime startTime, String startStation, LocalDateTime endTime, String endStation, float totalCost, float activationFee, float pricePerMinute) {
        this.uuid = uuid;
        this.customerUUID = customerUUID;
        this.startTime = startTime;
        this.startStation = startStation;
        this.endTime = endTime;
        this.endStation = endStation;
        this.totalCost = totalCost;
        this.activationFee = activationFee;
        this.pricePerMinute = pricePerMinute;
    }
}
