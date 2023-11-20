package org.finance.repository;

import org.finance.models.Expense;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ExpenseRepository extends CrudRepository<Expense, UUID> {
}
