package be.kdg.prog6.station.adapters.out.db.repositories;

import be.kdg.prog6.station.adapters.out.db.jpaEntites.LocationJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LocationRepository extends JpaRepository<LocationJpaEntity, UUID> {
    LocationJpaEntity findByUuid(UUID uuid);
}
