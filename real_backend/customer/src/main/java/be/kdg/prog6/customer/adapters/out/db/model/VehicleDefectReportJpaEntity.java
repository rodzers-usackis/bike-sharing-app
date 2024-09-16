package be.kdg.prog6.customer.adapters.out.db.model;

import be.kdg.prog6.customer.adapters.out.db.model.CustomerJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "vehicle_defect_report", schema = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDefectReportJpaEntity {

    @Id
    @Type(type = "uuid-char")
    private UUID uuid;
    private LocalDateTime timestamp = LocalDateTime.now();
    private String description;

    @ManyToOne
    private CustomerJpaEntity customer;
    private String vehicleId;
    private boolean isUnusable;
    private boolean hasBrokenWheel;
    private boolean hasFlatTyre;
    private boolean hasBrokenLight;
    private boolean hasBrokenBrake;
    private boolean hasOtherDefect;
    @Column(length = 10000)
    private String photo;


}
