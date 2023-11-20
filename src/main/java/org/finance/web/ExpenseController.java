package org.finance.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.finance.dto.ExpenseRequest;
import org.finance.dto.ExpenseResponse;
import org.finance.error.InvalidObjectException;
import org.finance.mapper.ExpenseMapper;
import org.finance.models.Customer;
import org.finance.models.Expense;
import org.finance.pagination.CustomPage;
import org.finance.service.CustomerService;
import org.finance.service.ExpenseService;
import org.finance.service.UserDetailsServiceImpl;
import org.finance.validation.ObjectValidator;
import org.finance.validation.ValidCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.Map;


@RestController
@RequestMapping("/expense")
@AllArgsConstructor
@Validated
public class ExpenseController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private ExpenseMapper expenseMapper;
    @Autowired
    private ObjectValidator validator;

    private final Integer PAGE_SIZE = 10;

    @PostMapping("")
    public ResponseEntity<ExpenseResponse> createExpense(@Valid @RequestBody ExpenseRequest expenseRequest,
                                                         Authentication authentication){
        Map<String, String> validationErrors = validator.validate(expenseRequest);
        if (validationErrors.size() != 0) {
            throw new InvalidObjectException("Invalid Expense Create", validationErrors);
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            Customer customer = customerService.findByUsername(userDetails.getUsername());
            Expense expense = expenseMapper.modelFromCreateRequest(expenseRequest);
            LocalDate currentDate = LocalDate.now();
            expense.setDate(currentDate);
            expense.setCustomer(customer);
            expenseService.save(expense);
            customer.getTransactions().add(expense);
            customer.setExpenseAmount(customer.getExpenseAmount() + expense.getAmount());
            customerService.save(customer);
            ExpenseResponse expenseResponse = expenseMapper.responseFromModel(expense);
            return ResponseEntity.status(201).body(expenseResponse);
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @GetMapping("trnxID")
    public ResponseEntity<ExpenseResponse> getById(@PathVariable String trnxID){
        Expense expense = expenseService.findById(trnxID);
        ExpenseResponse response = expenseMapper.responseFromModel(expense);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{category}")
    public ResponseEntity<ExpenseResponse> getByCategoryOrSub(@PathVariable String category){

        Expense expense = expenseService.findByCategoryOrSubcategory(category);
        ExpenseResponse response = expenseMapper.responseFromModel(expense);
        return ResponseEntity.ok(response);
    }
    @GetMapping(name = "", produces = "application/json")
    public CustomPage<ExpenseResponse> getAllPeople(@RequestParam(required = false, defaultValue = "0") Integer currentPage) {
        Page<ExpenseResponse> expensePage = expenseService.fetchAll(currentPage, PAGE_SIZE).map(expenseMapper::responseFromModel);
        return new CustomPage<>(expensePage);
    }
    @DeleteMapping("trnxID")
    public void deleteTrnxById(@PathVariable String trnxID){
        expenseService.deleteById(trnxID);
    }
    @DeleteMapping("all")
    public void deleteAll(){
        expenseService.deleteAll();
    }
}
