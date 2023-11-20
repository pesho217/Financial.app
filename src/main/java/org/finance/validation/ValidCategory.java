package org.finance.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidCategoryConstraint.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCategory {
    String message() default "Invalid category name";
    String[] allowedCategories() default {"Food", "Housing", "Transport", "Healthcare", "Education", "Entertainment", "Bills", "Shopping"};
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
