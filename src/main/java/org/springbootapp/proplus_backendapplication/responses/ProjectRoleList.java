package org.springbootapp.proplus_backendapplication.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springbootapp.proplus_backendapplication.dtos.ProjectDto;

import java.util.List;

@AllArgsConstructor
@Getter
public class ProjectRoleList {

    private List<ProjectDto> projects;
    private List<Integer> roles;
}
