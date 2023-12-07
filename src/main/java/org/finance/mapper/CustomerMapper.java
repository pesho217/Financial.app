package org.finance.mapper;

import org.finance.dto.CustomerRequest;
import org.finance.dto.CustomerResponse;

import org.finance.models.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Mapper
public interface CustomerMapper {

    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    Customer modelFromCreateRequest(CustomerRequest customerRequest);
    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "role", source = "role")
    @Mapping(target = "expenseAmount", source = "expenseAmount")
    @Mapping(target = "transactions", source = "transactions")
    @Mapping(target = "customerID", source = "customerID")
    CustomerResponse responseFromModel(Customer customer);

//    @Named("mapPassword")
//     default String mapPassword(String rawPassword){
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String encodedPassword = encoder.encode(rawPassword);
//        return encodedPassword;
//    }
}
