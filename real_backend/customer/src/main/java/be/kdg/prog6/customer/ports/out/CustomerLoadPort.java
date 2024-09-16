package be.kdg.prog6.customer.ports.out;

import be.kdg.prog6.customer.domain.Customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerLoadPort {
    Optional<Customer> loadCustomer(UUID customerUUID);
    Optional<List<Customer>> loadAllCustomers();
}
