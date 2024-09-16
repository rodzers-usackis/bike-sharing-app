package be.kdg.prog6.customer.adapters.out.db;

import be.kdg.prog6.customer.adapters.out.db.model.CustomerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerJpaRepository extends JpaRepository<CustomerJpaEntity, UUID> {


}
