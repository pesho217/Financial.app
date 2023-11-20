package org.finance.repository;

import org.finance.models.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface CustomerPagingRepository extends PagingAndSortingRepository<Customer, UUID> {

}
