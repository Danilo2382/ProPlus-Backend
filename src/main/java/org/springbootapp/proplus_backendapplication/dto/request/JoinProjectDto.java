package org.springbootapp.proplus_backendapplication.dto.request;

import jakarta.validation.constraints.NotNull;
import org.springbootapp.proplus_backendapplication.constants.ApplicationConstants;

public record JoinProjectDto(

        @NotNull(message = "Code " + ApplicationConstants.NOT_NULL_MESSAGE)
        String code,

        @NotNull(message = "Password " + ApplicationConstants.NOT_NULL_MESSAGE)
        String password

) {}
