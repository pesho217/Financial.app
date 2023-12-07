package org.finance.service;

import org.finance.error.NotFoundObjectException;
import org.finance.models.Customer;
import org.finance.models.Expense;
import org.finance.repository.ExpensePagingRepository;
import org.finance.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ExpenseService {

    @Autowired
    ExpensePagingRepository expensePagingRepo;
    @Autowired
    ExpenseRepository expenseRepo;

    public Expense findById(String TrnxID) {
        return expenseRepo.findById(UUID.fromString(TrnxID)).orElseThrow(() -> {
            throw new NotFoundObjectException("Transaction not found!", Expense.class.getName(), TrnxID);
        });
    }

    public List<Expense> findByCustomer(Customer customer) {
        return customer.getTransactions();
    }

    public Page<Expense> fetchAll(int currentPage, int PageSize) {
        return expensePagingRepo.findAll(PageRequest.of(currentPage, PageSize));
    }

    public void deleteById(String TrnxID) {

        expenseRepo.deleteById(UUID.fromString(TrnxID));
    }

    public void deleteAll() {
        expenseRepo.deleteAll();
    }

    public void save(Expense expense) {
        expenseRepo.save(expense);

    }

    public List<Expense> findByCategoryOrSubcategory(String category) {
        Iterable<Expense> expenses = expenseRepo.findAll();
        List<Expense> expenseList = new ArrayList<>();
        for (Expense expense : expenses) {
            if (expense.getCategory().equals(category)) {
                expenseList.add(expense);
            }
        }
        for (Expense expense : expenses) {
            if (expense.getSubcategory().equals(category)) {
                expenseList.add(expense);
            }
        }
        return expenseList;
    }
}
