package org.finance.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenRequest {
    public String username;
    public String password;
}
