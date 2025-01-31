package org.springbootapp.proplus_backendapplication.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springbootapp.proplus_backendapplication.constants.ApplicationConstants;
import org.springbootapp.proplus_backendapplication.model.Task;

import java.io.Serializable;

/**
 * DTO for {@link org.springbootapp.proplus_backendapplication.model.Task}
 */
public record TaskDto(

        @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
        @NotNull(message = "Name " + ApplicationConstants.NOT_NULL_MESSAGE)
        String name,

        @Size(min = 3, max = 500, message = "Description must be between 3 and 500 characters")
        @NotNull(message = "Description " + ApplicationConstants.NOT_NULL_MESSAGE)
        String description

) implements Serializable {

    public Task convertToTask() {
        return new Task(null, name, description, null, null, null, null);
    }
}