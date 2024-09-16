package be.kdg.prog6.maintenance.adapters.out.db.model;

import be.kdg.prog6.maintenance.domain.VehicleDefectReportStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "defect_report", schema="maintenance")
public class DefectReportJpaEntity {
    @Id
    @Type(type = "uuid-char")
    private UUID uuid;
    @Type(type = "uuid-char")
    private UUID customerContextReportId;
    private String description;
    @Type(type = "uuid-char")
    private UUID vehicleId;
    @Type(type = "uuid-char")
    private UUID customerId;
    private LocalDateTime timestamp;
    private boolean isUnusable;
    private boolean hasBrokenWheel;
    private boolean hasFlatTyre;
    private boolean hasBrokenLight;
    private boolean hasBrokenBrake;
    private boolean hasOtherDefect;
    @Column(length = 10000)
    private String photo;
    @Enumerated(EnumType.STRING)
    private VehicleDefectReportStatus status;

}
