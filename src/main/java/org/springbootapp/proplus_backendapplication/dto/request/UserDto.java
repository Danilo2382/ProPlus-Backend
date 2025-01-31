package org.springbootapp.proplus_backendapplication.dto.request;

import jakarta.validation.constraints.*;
import org.springbootapp.proplus_backendapplication.constants.ApplicationConstants;
import org.springbootapp.proplus_backendapplication.model.User;
import org.springbootapp.proplus_backendapplication.validation.ValidBirthday;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link org.springbootapp.proplus_backendapplication.model.User}
 */
public record UserDto(

        @Pattern(regexp = ApplicationConstants.USERNAME_VALIDATION_REGEX, message = ApplicationConstants.USERNAME_VALIDATION_MESSAGE)
        @NotNull(message = "Username " + ApplicationConstants.NOT_NULL_MESSAGE)
        String username,

        @Pattern(regexp = ApplicationConstants.EMAIL_VALIDATION_REGEX, message = ApplicationConstants.EMAIL_VALIDATION_MESSAGE)
        @NotNull(message = "Email " + ApplicationConstants.NOT_NULL_MESSAGE)
        String email,

        @Pattern(regexp = ApplicationConstants.PASSWORD_VALIDATION_REGEX, message = ApplicationConstants.PASSWORD_VALIDATION_MESSAGE)
        @NotNull(message = "Password " + ApplicationConstants.NOT_NULL_MESSAGE)
        String password,

        @Pattern(regexp = ApplicationConstants.NAME_VALIDATION_REGEX, message = "Name " + ApplicationConstants.NAME_VALIDATION_MESSAGE)
        @NotNull(message = "Name " + ApplicationConstants.NOT_NULL_MESSAGE)
        String name,

        @Pattern(regexp = ApplicationConstants.NAME_VALIDATION_REGEX, message = "Surname " + ApplicationConstants.NAME_VALIDATION_MESSAGE)
        @NotNull(message = "Surname " + ApplicationConstants.NOT_NULL_MESSAGE)
        String surname,

        @ValidBirthday
        String birthday

) implements Serializable {

    public User convertToUser() {
        return new User(null, username, email, password, name, surname, LocalDate.parse(birthday), null, null, null);
    }
}