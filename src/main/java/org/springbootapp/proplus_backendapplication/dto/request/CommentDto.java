package org.springbootapp.proplus_backendapplication.dto.request;

import jakarta.validation.constraints.NotNull;
import org.springbootapp.proplus_backendapplication.constants.ApplicationConstants;

import java.io.Serializable;

/**
 * DTO for {@link org.springbootapp.proplus_backendapplication.model.Comment}
 */
public record CommentDto(

        @NotNull(message = "Comment " + ApplicationConstants.NOT_NULL_MESSAGE)
        String text

) implements Serializable {}