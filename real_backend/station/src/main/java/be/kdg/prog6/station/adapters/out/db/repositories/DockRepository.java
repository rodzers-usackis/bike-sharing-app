package be.kdg.prog6.station.adapters.out.db.repositories;

import be.kdg.prog6.station.adapters.out.db.jpaEntites.DockJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface DockRepository extends JpaRepository<DockJpaEntity, UUID> {
    DockJpaEntity findByUuid(UUID uuid);

    @Query("SELECT d FROM DockJpaEntity d WHERE d.station = ?1")
    List<DockJpaEntity> findAllByStationUuid(UUID station);
}
