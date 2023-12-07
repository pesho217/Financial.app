package org.finance.service;

import org.finance.error.NotFoundObjectException;
import org.finance.models.Customer;
import org.finance.repository.CustomerPagingRepository;
import org.finance.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {
    @Autowired
    CustomerPagingRepository customerPagingRepo;
    @Autowired
    CustomerRepository customerRepo;
    public Customer findById(String customerID){
        return customerRepo.findById(UUID.fromString(customerID)).orElseThrow(() -> {
            throw new NotFoundObjectException("Customer not found!",Customer.class.getName(), customerID);});
    }

    public Page<Customer> fetchAll(int currentPage, int PageSize){
        return customerPagingRepo.findAll(PageRequest.of(currentPage,PageSize));
    }
    public void deleteById(String customerID) {

        customerRepo.deleteById(UUID.fromString(customerID));
    }
    public void deleteAll(){
        customerRepo.deleteAll();
    }
    public void save(Customer customer){
        customerRepo.save(customer);

    }
    public Customer findByUsername(String name){
        return customerRepo.findCustomerByUsername(name);
    }

}
