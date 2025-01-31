package org.springbootapp.proplus_backendapplication.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springbootapp.proplus_backendapplication.constants.ApplicationConstants;

public record ChangeRoleDto(

        @NotNull(message = "Username " + ApplicationConstants.NOT_NULL_MESSAGE)
        String username,

        @NotNull(message = "Role " + ApplicationConstants.NOT_NULL_MESSAGE)
        @Min(value = 0, message = ApplicationConstants.ROLE_VALIDATION_MESSAGE)
        @Max(value = 2, message = ApplicationConstants.ROLE_VALIDATION_MESSAGE)
        Integer role
) {}
