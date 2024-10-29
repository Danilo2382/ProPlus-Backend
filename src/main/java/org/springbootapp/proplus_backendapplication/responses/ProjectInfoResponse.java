package org.springbootapp.proplus_backendapplication.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springbootapp.proplus_backendapplication.dtos.ProjectDto;
import org.springbootapp.proplus_backendapplication.dtos.ProjectMemberDto;

@Data
@AllArgsConstructor
public class ProjectInfoResponse {

    private ProjectDto projectDto;
    private ProjectMemberDto projectMemberDto;
    private String message;
}
