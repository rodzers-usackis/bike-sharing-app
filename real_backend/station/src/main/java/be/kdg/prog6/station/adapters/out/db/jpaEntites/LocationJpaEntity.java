package be.kdg.prog6.station.adapters.out.db.jpaEntites;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;


@Entity
@Table(schema="station", name = "station.locations")
@Getter
@Setter
@NoArgsConstructor
public class LocationJpaEntity {

    @Id
    @Type(type = "uuid-char")
    private UUID uuid;

    @Setter
    private float longitude;

    @Setter
    private float latitude;
}
