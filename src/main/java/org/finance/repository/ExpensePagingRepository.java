package org.finance.repository;

import org.finance.models.Expense;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface ExpensePagingRepository extends PagingAndSortingRepository<Expense, UUID> {
}
