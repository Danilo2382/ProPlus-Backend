package org.springbootapp.proplus_backendapplication.dto.request;

import jakarta.validation.constraints.NotNull;
import org.springbootapp.proplus_backendapplication.constants.ApplicationConstants;

public record LoginDto(

        @NotNull(message = "Username " + ApplicationConstants.NOT_NULL_MESSAGE)
        String username,

        @NotNull(message = "Password " + ApplicationConstants.NOT_NULL_MESSAGE)
        String password
) {}
