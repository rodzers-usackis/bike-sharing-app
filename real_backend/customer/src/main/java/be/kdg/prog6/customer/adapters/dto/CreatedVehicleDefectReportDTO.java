package be.kdg.prog6.customer.adapters.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreatedVehicleDefectReportDTO {


    private UUID uuid;
    private String vehicleId;
    private String customerId;
    private String description;
    private boolean isUnusable;
    private boolean hasBrokenWheel;
    private boolean hasFlatTyre;
    private boolean hasBrokenLight;
    private boolean hasBrokenBrake;
    private boolean hasOtherDefect;
    private String photo;



}