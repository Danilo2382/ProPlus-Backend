package org.springbootapp.proplus_backendapplication.validation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import lombok.RequiredArgsConstructor;
import org.springbootapp.proplus_backendapplication.repository.ProjectRepository;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = ValidProjectId.ProjectIdValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidProjectId {

    String message() default "Project doesn't exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @RequiredArgsConstructor
    class ProjectIdValidator implements ConstraintValidator<ValidProjectId, Long> {

        private final ProjectRepository projectRepository;

        @Override
        public boolean isValid(Long projectId, ConstraintValidatorContext context) {
            if (!projectRepository.existsById(projectId)) {
                setCustomMessage(context, "Project with id " + projectId + " does not exist");
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
