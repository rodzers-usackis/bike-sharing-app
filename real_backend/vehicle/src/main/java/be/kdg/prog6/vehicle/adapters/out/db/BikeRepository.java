package be.kdg.prog6.vehicle.adapters.out.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface BikeRepository extends JpaRepository<BikeJpaEntity, UUID> {
    BikeJpaEntity findByUuid(UUID uuid);
}
