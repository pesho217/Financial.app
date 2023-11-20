package org.finance.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class ExpenseDto {
    public String category;
    public String subcategory;
    public String description;
    public String amount;
    public Date date;
    public UUID trnxID;
}
