package org.finance.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidCategoryConstraint implements ConstraintValidator<ValidCategory, String> {

            String [] allowedNames;
            @Override
            public void initialize(ValidCategory constraintAnnotation) {
                allowedNames = constraintAnnotation.allowedCategories();
            }
            @Override
            public boolean isValid(String category, ConstraintValidatorContext context) {
                for(String category1 : allowedNames){
                    if(category.equalsIgnoreCase(category1)){
                        return true;
                    }
                }return false;
            }
}
