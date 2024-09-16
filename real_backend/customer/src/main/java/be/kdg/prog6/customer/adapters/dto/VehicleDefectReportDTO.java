package be.kdg.prog6.customer.adapters.dto;

import be.kdg.prog6.customer.domain.VehicleDefect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class VehicleDefectReportDTO {
    private String vehicleId;
    private String customerId;
    private String description;
    @JsonProperty("isUnusable")
    private boolean isUnusable;
    private boolean hasBrokenWheel;
    private boolean hasFlatTyre;
    private boolean hasBrokenLight;
    private boolean hasBrokenBrake;
    private boolean hasOtherDefect;
    private String photo;

}