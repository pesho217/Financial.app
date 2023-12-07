package org.finance.repository;

import org.finance.models.Customer;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CustomerRepository extends CrudRepository<Customer, UUID> {
    Customer findCustomerByUsername(String username);
}
