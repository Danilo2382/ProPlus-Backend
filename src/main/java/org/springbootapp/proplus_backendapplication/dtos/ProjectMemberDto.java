package org.springbootapp.proplus_backendapplication.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Value;
import org.springbootapp.proplus_backendapplication.model.ProjectMember;

import java.io.Serializable;

/**
 * DTO for {@link ProjectMember}
 */
@Value
public class ProjectMemberDto implements Serializable {
    @NotNull
    Integer role;
}