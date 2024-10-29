package org.springbootapp.proplus_backendapplication.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link org.springbootapp.proplus_backendapplication.model.Comment}
 */
@Value
public class CommentDto implements Serializable {
    @NotNull
    @Size(max = 200)
    String text;
    @NotNull
    LocalDateTime timeStamp;
}