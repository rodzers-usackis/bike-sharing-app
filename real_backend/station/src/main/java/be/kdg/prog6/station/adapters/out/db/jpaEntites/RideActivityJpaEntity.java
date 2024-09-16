package be.kdg.prog6.station.adapters.out.db.jpaEntites;

import be.kdg.prog6.station.domain.DockAction;
import be.kdg.prog6.station.domain.RideActivity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "station_ride_activities")
@Table(schema="station", name = "station.ride_activities")
@Getter
@Setter
@NoArgsConstructor
public class RideActivityJpaEntity {
    @Id
    @Type(type = "uuid-char")
    private UUID rideActivityUUID;

    @Type(type = "uuid-char")
    private UUID rideUUID;

    @Type(type = "uuid-char")
    private UUID vehicleUUID;

    @Type(type = "uuid-char")
    private UUID customerUUID;

    @Enumerated(EnumType.STRING)
    private DockAction dockAction;

    @Type(type = "uuid-char")
    private UUID dockUUID;

    @Type(type = "uuid-char")
    private UUID stationUUID;

    @Setter
    private LocalDateTime time;
}
