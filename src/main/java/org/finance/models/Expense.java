package org.finance.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.finance.validation.ValidCategory;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Expense {
    @ValidCategory
    private String category;
    private String subcategory;
    private String description;
    private double amount;
    private LocalDate date;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID trnxID;
}
