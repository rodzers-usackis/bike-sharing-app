package be.kdg.prog6.station.adapters.out.db.mappers;

import be.kdg.prog6.station.adapters.out.db.jpaEntites.RideActivityJpaEntity;
import be.kdg.prog6.station.domain.RideActivity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RideActivityMapper {
    RideActivityMapper INSTANCE = Mappers.getMapper(RideActivityMapper.class);

    RideActivityJpaEntity mapToJpa(RideActivity rideActivity);

    RideActivity mapToDomain(RideActivityJpaEntity rideActivityJpaEntity);
}
