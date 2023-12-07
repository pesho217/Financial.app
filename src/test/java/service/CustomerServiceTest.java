package service;

import org.finance.FinancialApp;
import org.finance.models.Customer;
import org.finance.repository.CustomerPagingRepository;
import org.finance.repository.CustomerRepository;
import org.finance.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = {FinancialApp.class})
public class CustomerServiceTest {

    @InjectMocks
    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    CustomerPagingRepository customerPagingRepository;

    @Test
    void testFindById(){
        Customer customer = new Customer();
        UUID testId = UUID.randomUUID();
        customer.setCustomerID(testId);
        Mockito.when(customerRepository.findById(testId)).thenReturn(Optional.of(customer));
        Customer result = customerService.findById(testId.toString());
        assertEquals(customer,result);
    }

    @Test
    void testFetchAll(){
        int currentPage = 1;
        int PageSize = 10;
        Page<Customer> customerPage = new PageImpl<>(Collections.emptyList());
        Mockito.when(customerPagingRepository.findAll(PageRequest.of(currentPage,PageSize))).thenReturn(customerPage);
        Page<Customer> customerPage1 = customerService.fetchAll(currentPage,PageSize);
        assertEquals(customerPage,customerPage1);
    }
    @Test
    void deleteByIdTest(){
        UUID customerId = UUID.randomUUID();
        Customer customer = new Customer();
        customer.setCustomerID(customerId);
        customerService.deleteById(customerId.toString());
        Mockito.verify(customerRepository,Mockito.times(1)).deleteById(customerId);
    }
    @Test
    void saveTest(){
        Customer customer = new Customer();
        customerService.save(customer);
        Mockito.verify(customerRepository,Mockito.times(1)).save(customer);
    }
    @Test
    void deleteAll(){
        customerService.deleteAll();
        Mockito.verify(customerRepository,Mockito.times(1)).deleteAll();
    }
    @Test
    void findByUsername(){
        Customer customer = new Customer();
        customer.setUsername("ivan");
        Mockito.when(customerRepository.findCustomerByUsername("ivan")).thenReturn(customer);
        Customer result = customerService.findByUsername("ivan");
        assertEquals(customer,result);
    }
}
