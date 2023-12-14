package repository;

import jakarta.transaction.Transactional;
import org.finance.FinancialApp;
import org.finance.models.Expense;
import org.finance.repository.ExpenseRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.ClassBasedNavigableIterableAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Transactional
@SpringBootTest(classes = {FinancialApp.class})
public class ExpenseRepoTest {


        @Autowired
        private ExpenseRepository expenseRepository;

        @Test
        public void testSaveAndRetrieveExpense() {
            Expense expense = new Expense();
            expense.setCategory("Food");
            System.out.println(expense.getTrnxID());
            Expense savedExpense = expenseRepository.save(expense);
            if(expense.getTrnxID().equals(savedExpense.getTrnxID())){
                System.out.println("Expense saved successfully!");
            }else{
                System.out.println("Expense NOT saved successfully!");
                System.out.println(savedExpense.getTrnxID());
            }
            Expense retrievedExpense = expenseRepository.findById(expense.getTrnxID()).orElseThrow(()
                    -> new RuntimeException("TEST FAILED BECAUSE CANNOT FIND SUCH AN EXPENSE WITH THIS ID"));

            assertNotNull(retrievedExpense);
            assertEquals(retrievedExpense.getTrnxID(), expense.getTrnxID());
        }

        @Test
        public void testGetAllExpenses() {
            // Arrange
            Expense expense1 = new Expense();
            expense1.setDescription("this is test expense1");
            expense1.setCategory("Food");
            Expense expense2 = new Expense();
            expense2.setDescription("this is test expense2");
            expense2.setCategory("Food");

            // Act
            expenseRepository.saveAll(List.of(expense1, expense2));
            List<Expense> allExpenses = (List<Expense>) expenseRepository.findAll();

            // Assert
            assertEquals(2, allExpenses.size());
            assertEquals(allExpenses.get(0).getDescription(), "this is test expense1");
            assertEquals(allExpenses.get(1).getDescription(), "this is test expense2");
        }

        @Test
    void deleteTest(){
            Expense expense = new Expense();
            expense.setCategory("Food");
            expenseRepository.save(expense);
            assertNotNull(expense);
            expenseRepository.deleteById(expense.getTrnxID());
            assertEquals(expenseRepository.findById(expense.getTrnxID()),Optional.empty());


            Expense expense1 = new Expense();
            expense1.setCategory("Food");
            expenseRepository.save(expense1);
            assertNotNull(expense1);
            expenseRepository.delete(expense1);
            assertEquals(expenseRepository.findById(expense1.getTrnxID()), Optional.empty());
        }

        @Test
    void deleteAllTest(){
            Expense expense1 = new Expense();
            expense1.setDescription("this is test expense1");
            expense1.setCategory("Food");
            Expense expense2 = new Expense();
            expense2.setDescription("this is test expense2");
            expense2.setCategory("Food");

            // Act
            expenseRepository.saveAll(List.of(expense1, expense2));
            List<Expense> allExpenses = (List<Expense>) expenseRepository.findAll();

            // Assert
            assertEquals(2, allExpenses.size());
            assertEquals(allExpenses.get(0).getDescription(), "this is test expense1");
            assertEquals(allExpenses.get(1).getDescription(), "this is test expense2");

            expenseRepository.deleteAll();
            List<Expense> deletedExpenses = (List<Expense>) expenseRepository.findAll();
            assertEquals(0, deletedExpenses.size());
        }
        @Test
    void saveTest(){
            Expense expense1 = new Expense();
            expense1.setDescription("this is test expense1");
            expense1.setCategory("Food");
            expenseRepository.save(expense1);
            assertNotNull(expenseRepository.findById(expense1.getTrnxID()));
        }

    }

