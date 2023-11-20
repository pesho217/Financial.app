package org.finance.dto;

import lombok.Builder;
import lombok.Data;
import org.finance.models.Customer;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;
@Data
@Builder
public class ExpenseResponse {
    public String category;
    public String subcategory;
    public String description;
    public String amount;
    public LocalDate date;
    public UUID trnxID;
    public Customer customer;
}
