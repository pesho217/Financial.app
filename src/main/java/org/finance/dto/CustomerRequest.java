package org.finance.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
   @Size(min = 1, max = 10)
   public String username;

   @Size(min = 5, max = 15)
   public String password;
}
