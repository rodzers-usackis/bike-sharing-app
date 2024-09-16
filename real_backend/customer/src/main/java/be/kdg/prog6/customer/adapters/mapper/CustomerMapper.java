package be.kdg.prog6.customer.adapters.mapper;

import be.kdg.prog6.customer.adapters.out.db.model.CustomerJpaEntity;
import be.kdg.prog6.customer.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerJpaEntity map(Customer customer);

    @Mapping(target="uuid", source="uuid")
    Customer map(CustomerJpaEntity customerJpaEntity);
}
