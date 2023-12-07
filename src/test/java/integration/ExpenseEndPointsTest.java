package integration;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest(classes = ExpenseEndPointsTest.class)
@AutoConfigureMockMvc
@Tag("integration")
public class ExpenseEndPointsTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "nikolai", password = "nik217" , roles = {"USER"})
    void shouldAddValidExpense() throws Exception {
        mockMvc.perform(
                        post("/expense")
                                .contentType(MediaType.APPLICATION_JSON)
//                                .header("Content-Type", "application/json")
                                .content("""
                {"category": "Food",
                "subcategory":"Groceries",
                "amount":"20",
                "description":"I bought some groceries"}
                """
)
                ).andExpect(status().isCreated())
                .andExpect(jsonPath("$.category").value("Food"))
                .andExpect(jsonPath("$.subcategory").value("Groceries"))
                .andExpect(jsonPath("$.amount").value("20"))
                .andExpect(jsonPath("$.description").value("I bought some groceries"));
    }

}
