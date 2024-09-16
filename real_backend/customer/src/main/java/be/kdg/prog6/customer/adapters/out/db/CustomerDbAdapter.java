package be.kdg.prog6.customer.adapters.out.db;

import be.kdg.prog6.customer.adapters.mapper.CustomerMapper;
import be.kdg.prog6.customer.adapters.out.db.model.CustomerJpaEntity;
import be.kdg.prog6.customer.domain.Customer;
import be.kdg.prog6.customer.ports.out.CustomerLoadPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CustomerDbAdapter implements CustomerLoadPort {

    private final CustomerJpaRepository customerJpaRepository;
    private final CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    public CustomerDbAdapter(CustomerJpaRepository customerJpaRepository) {
        this.customerJpaRepository = customerJpaRepository;
    }

    @Override
    public Optional<Customer> loadCustomer(UUID customerUUID) {
        CustomerJpaEntity customerJpaEntity = customerJpaRepository.findById(customerUUID).orElse(null);
        return Optional.ofNullable(customerMapper.map(customerJpaEntity));
    }

    @Override
    public Optional<List<Customer>> loadAllCustomers() {
        List<CustomerJpaEntity> customerJpaEntities = customerJpaRepository.findAll();
        List<Customer> mappedCustomers = customerJpaEntities.stream().map(customerMapper::map).toList();
        return Optional.ofNullable(mappedCustomers);
    }

}
