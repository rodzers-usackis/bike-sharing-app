package be.kdg.prog6.station.adapters.out.db.jpaEntites;

import be.kdg.prog6.station.domain.DockState;
import io.micrometer.core.lang.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.UUID;


@Entity
@Table(schema="station", name = "station.docks")
@Getter
@Setter
@NoArgsConstructor
public class DockJpaEntity {

    @Id
    @Type(type = "uuid-char")
    private UUID uuid;

    @Setter
    @Type(type = "uuid-char")
    private UUID station;

    @Setter
    private int dockNumber;

    @Setter
    @Enumerated(EnumType.STRING)
    private DockState dockState;

    @Setter
    @Type(type = "uuid-char")
    @Nullable
    private UUID vehicle;

    public DockJpaEntity(UUID uuid) {
        this.uuid = uuid;
    }
}
