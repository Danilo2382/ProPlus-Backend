package org.springbootapp.proplus_backendapplication.converters;

import org.springbootapp.proplus_backendapplication.dtos.ProjectDto;
import org.springbootapp.proplus_backendapplication.model.Project;

public class ProjectConverter {

    public static Project convertDto(ProjectDto projectDto) {
        Project project = new Project();
        project.setId(projectDto.getId());
        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());
        project.setStartDate(projectDto.getStartDate());
        project.setEndDate(projectDto.getEndDate());
        project.setCreateDate(projectDto.getCreateDate());
        project.setStatus(projectDto.getStatus());
        project.setCode(projectDto.getCode());
        project.setPassword(projectDto.getPassword());
        return project;
    }
}
