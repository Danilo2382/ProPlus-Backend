package org.springbootapp.proplus_backendapplication.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springbootapp.proplus_backendapplication.constants.ApplicationConstants;

public record CreateTaskDto(

        @NotNull(message = "Username " + ApplicationConstants.NOT_NULL_MESSAGE)
        String username,

        @Valid
        @NotNull(message = "Task " + ApplicationConstants.NOT_NULL_MESSAGE)
        TaskDto taskDto

) {}
