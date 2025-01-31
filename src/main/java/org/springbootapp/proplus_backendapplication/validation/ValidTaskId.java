package org.springbootapp.proplus_backendapplication.validation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import lombok.RequiredArgsConstructor;
import org.springbootapp.proplus_backendapplication.repository.TaskRepository;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = ValidTaskId.TaskIdValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTaskId {

    String message() default "Task doesn't exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @RequiredArgsConstructor
    class TaskIdValidator implements ConstraintValidator<ValidTaskId, Long> {

        private final TaskRepository taskRepository;

        @Override
        public boolean isValid(Long projectId, ConstraintValidatorContext context) {
            if (!taskRepository.existsById(projectId)) {
                setCustomMessage(context, "Task with id " + projectId + " does not exist");
                return false;
            }
            return true;
        }

        private void setCustomMessage(ConstraintValidatorContext context, String message) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        }
    }
}
