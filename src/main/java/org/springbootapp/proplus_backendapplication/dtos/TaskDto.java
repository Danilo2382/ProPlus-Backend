package org.springbootapp.proplus_backendapplication.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link org.springbootapp.proplus_backendapplication.model.Task}
 */
@Value
public class TaskDto implements Serializable {
    @NotNull
    Integer id;
    @NotNull
    @Size(max = 45)
    String name;
    @NotNull
    @Size(max = 200)
    String description;
    @NotNull
    LocalDateTime createDate;
    @NotNull
    Integer status;
}