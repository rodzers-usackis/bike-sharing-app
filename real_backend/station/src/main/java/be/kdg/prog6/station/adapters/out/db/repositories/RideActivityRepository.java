package be.kdg.prog6.station.adapters.out.db.repositories;

import be.kdg.prog6.station.adapters.out.db.jpaEntites.RideActivityJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

public interface RideActivityRepository extends JpaRepository<RideActivityJpaEntity, UUID> {
    @Query ("SELECT ra FROM station_ride_activities ra WHERE ra.customerUUID = ?1 AND ra.dockAction = 'TAKE_OUT' ORDER BY ra.time DESC")
    List<RideActivityJpaEntity> findTakeOutBikeRideActivityFromCustomerUUID(UUID customerUUID);
}
