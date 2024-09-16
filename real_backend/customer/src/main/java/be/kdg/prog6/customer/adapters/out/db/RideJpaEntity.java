package be.kdg.prog6.customer.adapters.out.db;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
@Entity(name = "customer_ride")
@Table(name = "customer.ride", schema = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RideJpaEntity {
    @Id
    @Type(type = "uuid-char")
    private UUID uuid;
    @Type(type = "uuid-char")
    private UUID customerUUID;
    private LocalDateTime startTime;
    private String startStation;
    private LocalDateTime endTime;
    private String endStation;
    private float totalCost;
    private float activationFee;
    private float pricePerMinute;
}
