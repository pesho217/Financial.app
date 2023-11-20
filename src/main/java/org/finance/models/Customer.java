package org.finance.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.List;
import java.util.UUID;


@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Role role = Role.USER;

    @Id
    @GeneratedValue
    @Column(name = "customerid")
    @Setter(AccessLevel.NONE)
    private UUID customerID;
    private double expenseAmount;


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    @Column(name = "transactions")
    private List<Expense> transactions;
}
