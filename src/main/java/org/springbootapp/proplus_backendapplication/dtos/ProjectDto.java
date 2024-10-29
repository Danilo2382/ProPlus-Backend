package org.springbootapp.proplus_backendapplication.dtos;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;
import org.springbootapp.proplus_backendapplication.views.ProjectDtoView;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link org.springbootapp.proplus_backendapplication.model.Project}
 */
@Value
public class ProjectDto implements Serializable {
    @JsonView(ProjectDtoView.AllAttributes.class)
    @NotNull
    Integer id;
    @JsonView(ProjectDtoView.BasicAttributes.class)
    @NotNull
    @Size(max = 45)
    String name;
    @JsonView(ProjectDtoView.BasicAttributes.class)
    @NotNull
    @Size(max = 1000)
    String description;
    @JsonView(ProjectDtoView.BasicAttributes.class)
    @NotNull
    LocalDateTime startDate;
    @JsonView(ProjectDtoView.BasicAttributes.class)
    @NotNull
    LocalDateTime endDate;
    @JsonView(ProjectDtoView.AllAttributes.class)
    @NotNull
    LocalDateTime createDate;
    @JsonView(ProjectDtoView.AllAttributes.class)
    @NotNull
    Integer status;
    @JsonView(ProjectDtoView.AllAttributes.class)
    @NotNull
    @Size(max = 45)
    String code;
    @JsonView(ProjectDtoView.BasicAttributes.class)
    @NotNull
    @Size(max = 45)
    String password;
}