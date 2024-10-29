package org.springbootapp.proplus_backendapplication.formaters;

import org.springbootapp.proplus_backendapplication.dtos.ProjectDto;
import org.springbootapp.proplus_backendapplication.model.Project;

public class ProjectFormater {

    public static ProjectDto formatDto(Project project) {
        return new ProjectDto(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getStartDate(),
                project.getEndDate(),
                project.getCreateDate(),
                project.getStatus(),
                project.getCode(),
                project.getPassword()
        );
    }
}
