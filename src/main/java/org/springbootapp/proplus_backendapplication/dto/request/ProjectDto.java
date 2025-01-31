package org.springbootapp.proplus_backendapplication.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springbootapp.proplus_backendapplication.constants.ApplicationConstants;
import org.springbootapp.proplus_backendapplication.model.Project;
import org.springbootapp.proplus_backendapplication.validation.ValidDateTime;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link org.springbootapp.proplus_backendapplication.model.Project}
 */
public record ProjectDto(

        @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
        @NotNull(message = "Name " + ApplicationConstants.NOT_NULL_MESSAGE)
        String name,

        @Size(min = 3, max = 500, message = "Description must be between 3 and 500 characters")
        @NotNull(message = "Description " + ApplicationConstants.NOT_NULL_MESSAGE)
        String description,

        @ValidDateTime
        String startDateTime,

        @ValidDateTime
        String endDateTime,

        @Pattern(regexp = ApplicationConstants.PASSWORD_VALIDATION_REGEX, message = ApplicationConstants.PASSWORD_VALIDATION_MESSAGE)
        @NotNull(message = "Password " + ApplicationConstants.NOT_NULL_MESSAGE)
        String password

) implements Serializable {

    public Project convertToProject() {
        return new Project(null, name, description, LocalDateTime.parse(startDateTime), LocalDateTime.parse(endDateTime), null, null, null, password, null);
    }
}