package org.finance.dto;

import lombok.Builder;
import lombok.Data;
import org.finance.models.Expense;
import org.finance.models.Role;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class CustomerResponse {
    public String username;
    public String password;
    private Role role;
    public UUID customerID;
    public double expenseAmount;
    public List<Expense> transactions;
}
