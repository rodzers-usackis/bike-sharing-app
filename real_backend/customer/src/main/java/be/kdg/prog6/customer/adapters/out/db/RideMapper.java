package be.kdg.prog6.customer.adapters.out.db;

import be.kdg.prog6.customer.domain.Ride;
import be.kdg.prog6.customer.domain.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RideMapper {
    RideMapper INSTANCE = Mappers.getMapper(RideMapper.class);

    RideJpaEntity mapToJpa(Ride ride);

    Ride mapToDomain(RideJpaEntity rideJpaEntity);

}
