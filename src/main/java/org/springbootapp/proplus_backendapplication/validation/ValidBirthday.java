package org.springbootapp.proplus_backendapplication.validation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import org.springbootapp.proplus_backendapplication.constants.ApplicationConstants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;

@Constraint(validatedBy = ValidBirthday.BirthdayValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidBirthday {

    String message() default "User must be of legal age";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class BirthdayValidator implements ConstraintValidator<ValidBirthday, String> {

        @Override
        public boolean isValid(String birthdayString, ConstraintValidatorContext context) {
            if (birthdayString == null) {
                setCustomMessage(context, "Birthday " + ApplicationConstants.NOT_NULL_MESSAGE);
                return false;
            }
            try {
                LocalDate birthday = LocalDate.parse(birthdayString);
                LocalDate currentDate = LocalDate.now();
                if (birthday.isAfter(currentDate) || birthday.isEqual(currentDate))
                    return false;
                return Period.between(birthday, currentDate).getYears() >= 18;
            } catch (DateTimeParseException ex) {
                setCustomMessage(context, ApplicationConstants.DATE_VALIDATION_MESSAGE);
                return false;
            }
        }

        private void setCustomMessage(ConstraintValidatorContext context, String message) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        }
    }

}
