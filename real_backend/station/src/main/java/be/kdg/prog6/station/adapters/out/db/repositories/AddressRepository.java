package be.kdg.prog6.station.adapters.out.db.repositories;

import be.kdg.prog6.station.adapters.out.db.jpaEntites.AddressJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<AddressJpaEntity, UUID> {
    AddressJpaEntity findByUuid(UUID uuid);
}
