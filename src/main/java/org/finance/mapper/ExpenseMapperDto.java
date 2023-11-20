package org.finance.mapper;

import org.finance.dto.ExpenseDto;
import org.finance.dto.ExpenseRequest;
import org.finance.dto.ExpenseResponse;
import org.finance.models.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface ExpenseMapperDto {



    @Mapping(target = "category", source = "category")
    @Mapping(target = "subcategory", source = "subcategory")
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "description", source = "description")
    ExpenseDto modelToDto(Expense expense);
    Expense dtoToModel(ExpenseDto expenseDto);

}
