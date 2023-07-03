package org.example.carservice.lib;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    private static final String EMAIL_VALIDATION_REGEX = "^(.+)@(.+)$";
    private static final Logger logger = LogManager.getLogger(EmailValidator.class);

    @Override
    public boolean isValid(String field, ConstraintValidatorContext context) {
        logger.info("Check email. Params email = {}", field);
        boolean emailIsValid = field != null && field.matches(EMAIL_VALIDATION_REGEX);
        if (!emailIsValid) {
            logger.error("Is not valid email. Params email = {}", field);
        }
        return emailIsValid;
    }
}
