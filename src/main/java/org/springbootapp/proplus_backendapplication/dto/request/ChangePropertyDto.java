package org.springbootapp.proplus_backendapplication.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springbootapp.proplus_backendapplication.constants.ApplicationConstants;

public record ChangePropertyDto(

        @Pattern(regexp = ApplicationConstants.USERNAME_VALIDATION_REGEX, message = ApplicationConstants.USERNAME_VALIDATION_MESSAGE,
                groups = ChangeUsername.class)
        @Pattern(regexp = ApplicationConstants.EMAIL_VALIDATION_REGEX, message = ApplicationConstants.EMAIL_VALIDATION_MESSAGE,
                groups = ChangeEmail.class)
        @Pattern(regexp = ApplicationConstants.PASSWORD_VALIDATION_REGEX, message = ApplicationConstants.PASSWORD_VALIDATION_MESSAGE,
                groups = ChangePassword.class)
        @NotNull(message = "Change property " + ApplicationConstants.NOT_NULL_MESSAGE, groups = {ChangeUsername.class, ChangeEmail.class, ChangePassword.class})
        String property

) {

    public interface ChangeUsername {}
    public interface ChangeEmail {}
    public interface ChangePassword {}

}