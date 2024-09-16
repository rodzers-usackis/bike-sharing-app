package be.kdg.prog6.station.adapters.out.db.repositories;

import be.kdg.prog6.station.adapters.out.db.jpaEntites.StationJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StationRepository extends JpaRepository<StationJpaEntity, UUID> {
    StationJpaEntity findByUuid(UUID uuid);
}
