package org.springbootapp.proplus_backendapplication.validation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@Constraint(validatedBy = ValidDateTime.DateTimeValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDateTime {

    String message() default "Date time must be in valid format (yyyy-MM-ddTHH:mm:ss)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class DateTimeValidator implements ConstraintValidator<ValidDateTime, String> {

        @Override
        public boolean isValid(String dateTime, ConstraintValidatorContext context) {
            if (dateTime == null)
                return false;
            try {
                LocalDateTime.parse(dateTime);
                return true;
            } catch (DateTimeParseException ex) {
                return false;
            }
        }
    }
}
