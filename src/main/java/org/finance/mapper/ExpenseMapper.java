package org.finance.mapper;

import org.finance.dto.ExpenseRequest;
import org.finance.dto.ExpenseResponse;
import org.finance.models.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(uses = ExpenseMapperDto.class)
public interface ExpenseMapper {


        @Mapping(target = "category", source = "category")
        @Mapping(target = "subcategory", source = "subcategory")
        @Mapping(target = "amount", source = "amount")
        @Mapping(target = "description", source = "description")
        @Mapping(target = "customer", source = "customer")
        ExpenseResponse responseFromModel(Expense expense);

        @Mapping(target = "category", source = "category")
        @Mapping(target = "subcategory", source = "subcategory")
        @Mapping(target = "amount", source = "amount")
        @Mapping(target = "description", source = "description")
        Expense modelFromCreateRequest(ExpenseRequest personCreateDto);


}
