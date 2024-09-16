package be.kdg.prog6.customer.adapters.out.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface RideJpaRepository extends JpaRepository<RideJpaEntity, UUID> {
    @Query("SELECT r FROM customer_ride r WHERE r.customerUUID = ?1")
    List<RideJpaEntity> findAllRidesByCustomerUUID(UUID customerUUID);
}

