package org.springbootapp.proplus_backendapplication.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springbootapp.proplus_backendapplication.dtos.TaskDto;

@AllArgsConstructor
@Getter
public class CreateTaskRequest {

    private Integer idProject;
    private TaskDto task;
    private String username;
}
