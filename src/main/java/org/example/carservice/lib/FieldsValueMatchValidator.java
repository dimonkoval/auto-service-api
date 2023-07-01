package org.example.carservice.lib;

import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.carservice.dto.request.UserRequestDto;
import org.springframework.beans.BeanWrapperImpl;

public class FieldsValueMatchValidator
        implements ConstraintValidator<FieldsValueMatch, Object> {
    private static final Logger logger = LogManager.getLogger(FieldsValueMatchValidator.class);
    private String field;
    private String fieldMatch;

    public void initialize(FieldsValueMatch constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }

    public boolean isValid(Object value,
                           ConstraintValidatorContext context) {
        Object fieldValue = new BeanWrapperImpl(value)
                .getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(value)
                .getPropertyValue(fieldMatch);
        boolean repeatPasswordIsValid = Objects.equals(fieldValue, fieldMatchValue);
        if (!repeatPasswordIsValid) {
            logger.error("Passwords do not match! User by email = {}",
                    ((UserRequestDto)value).getEmail());
        }
        return repeatPasswordIsValid;
    }
}
