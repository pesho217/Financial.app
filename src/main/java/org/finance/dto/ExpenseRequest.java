package org.finance.dto;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.finance.validation.ValidCategory;

@Data
@Builder
public class ExpenseRequest {

    @ValidCategory
    public String category;
    public String subcategory;
    public String description;
    public String amount;
}
