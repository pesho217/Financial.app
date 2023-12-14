package repository;

import jakarta.transaction.Transactional;
import org.checkerframework.checker.units.qual.C;
import org.finance.FinancialApp;
import org.finance.models.Customer;
import org.finance.models.Expense;
import org.finance.repository.CustomerRepository;
import org.finance.repository.ExpenseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Transactional
@SpringBootTest(classes = {FinancialApp.class})
public class CustomerRepoTest {
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testFindByID() {
       Customer customer = new Customer();
        System.out.println(customer.getCustomerID());
        Customer savedCustomer = customerRepository.save(customer);
        if(customer.getCustomerID().equals(savedCustomer.getCustomerID())){
            System.out.println("Customer saved successfully!");
        }else{
            System.out.println("Customer NOT saved successfully!");
            System.out.println(savedCustomer.getCustomerID());
        }
        Customer retrievedCustomer = customerRepository.findById(customer.getCustomerID()).orElseThrow(()
                -> new RuntimeException("TEST FAILED BECAUSE CANNOT FIND SUCH A CUSTOMER WITH THIS ID"));

        assertNotNull(retrievedCustomer);
        assertEquals(retrievedCustomer.getCustomerID(), customer.getCustomerID());
    }

    @Test
    public void testGetAllCustomers() {
        // Arrange
        Customer customer2 = new Customer();
        Customer customer1 = new Customer();
        customer2.setUsername("Ivan");
        customer1.setUsername("Valio");

        // Act

        List<Customer> allSavedCustomers = new ArrayList<>();
        customerRepository.saveAll(List.of(customer1, customer2)).forEach(allSavedCustomers::add);
        List<Customer> allCustomers = (List<Customer>) customerRepository.findAll();

        // Assert
        assertEquals(2, allCustomers.size());
        assertEquals(allSavedCustomers,allCustomers);
        assertEquals(allCustomers.get(0).getUsername(), "Valio");
        assertEquals(allCustomers.get(1).getUsername(), "Ivan");
    }

    @Test
    void deleteTest(){
        Customer customer = new Customer();
        customerRepository.save(customer);
        assertNotNull(customer);
        customerRepository.deleteById(customer.getCustomerID());
        assertEquals(customerRepository.findById(customer.getCustomerID()), Optional.empty());


        Customer customer1 = new Customer();
        customerRepository.save(customer1);
        assertNotNull(customer1);
        customerRepository.delete(customer1);
        assertEquals(customerRepository.findById(customer1.getCustomerID()), Optional.empty());
    }

    @Test
    void deleteAllTest(){
        Customer customer2 = new Customer();
        Customer customer1 = new Customer();
        customer2.setUsername("Ivan");
        customer1.setUsername("Valio");

        // Act
        customerRepository.saveAll(List.of(customer1, customer2));
        List<Customer> allCustomers = (List<Customer>) customerRepository.findAll();

        // Assert
        assertEquals(2, allCustomers.size());
        assertEquals(allCustomers.get(0).getUsername(), "Valio");
        assertEquals(allCustomers.get(1).getUsername(), "Ivan");

        customerRepository.deleteAll();
        List<Customer> deletedCustomers = (List<Customer>) customerRepository.findAll();
        assertEquals(0, deletedCustomers.size());
    }
    @Test
    void saveTest(){
        Customer customer = new Customer();
        customerRepository.save(customer);
        assertNotNull(customerRepository.findById(customer.getCustomerID()));
    }

}
