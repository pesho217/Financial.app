package service;

import com.fasterxml.jackson.core.type.TypeReference;
import integration.ExpenseEndPointsTest;
import org.finance.FinancialApp;
import org.finance.error.NotFoundObjectException;
import org.finance.models.Customer;
import org.finance.models.Expense;
import org.finance.repository.ExpensePagingRepository;
import org.finance.repository.ExpenseRepository;
import org.finance.service.ExpenseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {FinancialApp.class})
    class ExpenseServiceTest {



    @Mock
    private ExpensePagingRepository expensePagingRepository;

    @Mock
    private ExpenseRepository expenseRepository;

    @InjectMocks
    private ExpenseService expenseService;

    @Test
    void findById() {
        UUID trnxID = UUID.randomUUID();
        Expense expense = new Expense();
        expense.setTrnxID(trnxID);

        Mockito.when(expenseRepository.findById(expense.getTrnxID())).thenReturn(Optional.of(expense));

        Expense result = expenseService.findById(expense.getTrnxID().toString());

        assertEquals(expense, result);
    }

    @Test
    void findByCustomer() {
        Customer customer = Mockito.mock(Customer.class);
        List<Expense> expenses = Collections.emptyList();

        Mockito.when(customer.getTransactions()).thenReturn(expenses);

        List<Expense> result = expenseService.findByCustomer(customer);

        assertEquals(expenses, result);
    }

    @Test
    void fetchAll() {
        int currentPage = 1;
        int pageSize = 10;
        Page<Expense> expensePage = new PageImpl<>(Collections.emptyList());

        Mockito.when(expensePagingRepository.findAll(PageRequest.of(currentPage, pageSize))).thenReturn(expensePage);

        Page<Expense> result = expenseService.fetchAll(currentPage, pageSize);

        assertEquals(expensePage, result);
    }

    @Test
    void deleteById() {
        UUID trnxId = UUID.randomUUID();

        expenseService.deleteById(trnxId.toString());

        Mockito.verify(expenseRepository, Mockito.times(1)).deleteById(UUID.fromString(trnxId.toString()));
    }

    @Test
    void deleteAll() {
        expenseService.deleteAll();

        Mockito.verify(expenseRepository, Mockito.times(1)).deleteAll();
    }

    @Test
    void save() {
        Expense expense = new Expense();
        expenseService.save(expense);
        Mockito.verify(expenseRepository, Mockito.times(1)).save((expense));
    }

    @Test
    void testFindByCategoryOrSubcategory() {
        // Mock data
        Expense expense1 = new Expense();
        Expense expense2 = new Expense();
        Expense expense3 = new Expense();
        expense1.setCategory("category1");
        expense2.setCategory("category2");
        expense3.setCategory("category2");
        expense1.setSubcategory("subcategory1");
        expense2.setSubcategory("subcategory1");
        expense3.setSubcategory("subcategory3");

        List<Expense> mockExpenses = Arrays.asList(expense1, expense2, expense3);

        // Mock behavior of expenseRepo.findAll()
        Mockito.when(expenseRepository.findAll()).thenReturn(mockExpenses);


        List<Expense> result = expenseService.findByCategoryOrSubcategory("category2");

        assertTrue(result.contains(expense3));
        assertFalse(result.contains(expense1));
        assertEquals(2, result.size());

        result = expenseService.findByCategoryOrSubcategory("subcategory3");
        assertEquals(1, result.size());

        assertTrue(result.contains(expense3));
    }
}

