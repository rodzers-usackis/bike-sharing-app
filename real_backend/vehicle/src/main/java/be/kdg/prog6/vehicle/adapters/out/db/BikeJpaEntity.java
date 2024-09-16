package be.kdg.prog6.vehicle.adapters.out.db;

import be.kdg.prog6.vehicle.domain.VehicleState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(schema="vehicle", name = "vehicle.bikes")
@Getter
@Setter
@NoArgsConstructor
public class BikeJpaEntity {

    @Id
    @Type(type = "uuid-char")
    private UUID uuid;

    @Column
    private float price;

    @Column
    private float costPerMinute;

    @Column
    @Enumerated(EnumType.STRING)
    private VehicleState vehicleState;

    public BikeJpaEntity(UUID uuid) {
        this.uuid = uuid;
    }
}
