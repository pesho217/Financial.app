package org.finance.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class ObjectValidator {
    public final ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
    private final Validator validator = vf.getValidator();

    public Map<String, String> validate(Object object) {
        Set<ConstraintViolation<Object>> validationFailures = validator.validate(object);

        Map<String, String> violationsMap = new HashMap<>();
        for (ConstraintViolation<Object> violation : validationFailures) {
            violationsMap.put(violation.getPropertyPath().toString(), violation.getMessage());
        }

        return violationsMap;
    }

}

