package be.kdg.prog6.station.adapters.out.db.jpaEntites;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(schema="station", name = "station.stations")
@Getter
@Setter
@NoArgsConstructor
public class StationJpaEntity {
    @Id
    @Type(type = "uuid-char")
    private UUID uuid;

    @Setter
    @Type(type = "uuid-char")
    private UUID address;

    @Setter
    @Type(type = "uuid-char")
    private UUID location;

    @Setter
    private String name;

    public StationJpaEntity(UUID uuid) {
        this.uuid = uuid;
    }
}
