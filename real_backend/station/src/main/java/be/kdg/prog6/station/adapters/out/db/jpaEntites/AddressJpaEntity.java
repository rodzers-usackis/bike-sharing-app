package be.kdg.prog6.station.adapters.out.db.jpaEntites;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;


@Entity
@Table(schema="station", name = "station.addresses")
@Getter
@Setter
@NoArgsConstructor
public class AddressJpaEntity {

    @Id
    @Type(type = "uuid-char")
    private UUID uuid;

    @Setter
    private String city;

    @Setter
    private String streetName;

    @Setter
    private int streetNumber;
}
