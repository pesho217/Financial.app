package org.finance.web;

import lombok.AllArgsConstructor;
import org.finance.dto.CustomerRequest;
import org.finance.dto.CustomerResponse;
import org.finance.mapper.CustomerMapper;
import org.finance.models.Customer;
import org.finance.pagination.CustomPage;
import org.finance.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
@Autowired
private CustomerMapper customerMapper;
@Autowired
private CustomerService customerService;


    private final Integer PAGE_SIZE = 10;

    @PostMapping("")
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest customerRequest){
        Customer customer= customerMapper.modelFromCreateRequest(customerRequest);
        customerService.save(customer);
        CustomerResponse customerResponse = customerMapper.responseFromModel(customer);
        return ResponseEntity.status(201).body(customerResponse);
    }

    @GetMapping("/{customerID}")
    public ResponseEntity<CustomerResponse> getById(@PathVariable String customerID){
        Customer customer = customerService.findById(customerID);
        CustomerResponse customerResponse = customerMapper.responseFromModel(customer);
        return ResponseEntity.ok(customerResponse);
    }
    @GetMapping("")
    public ResponseEntity<CustomerResponse> getByAuth(Authentication authentication){
        Customer customer = customerService.findByUsername(authentication.getName());
        CustomerResponse customerResponse = customerMapper.responseFromModel(customer);
        return ResponseEntity.ok(customerResponse);
    }
    @GetMapping("/{username}")
    public ResponseEntity<CustomerResponse> getByUsername(@PathVariable String username){
        Customer customer = customerService.findByUsername(username);
        CustomerResponse customerResponse = customerMapper.responseFromModel(customer);
        return ResponseEntity.ok(customerResponse);
    }
    @GetMapping("all")
    public CustomPage<CustomerResponse> getAllCustomers(@RequestParam(required = false, defaultValue = "0") Integer currentPage) {
        Page<CustomerResponse> customerPage = customerService.fetchAll(currentPage, PAGE_SIZE).map(customerMapper::responseFromModel);
        return new CustomPage<>(customerPage);
    }
    @DeleteMapping("/{customerID}")
    public void deleteCustomerById(@PathVariable String customerID){
        customerService.deleteById(customerID);
    }
    @DeleteMapping("all")
    public void deleteAll(){
        customerService.deleteAll();
    }
}
