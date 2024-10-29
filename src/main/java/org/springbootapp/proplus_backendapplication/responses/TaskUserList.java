package org.springbootapp.proplus_backendapplication.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springbootapp.proplus_backendapplication.dtos.TaskDto;

import java.util.List;

@AllArgsConstructor
@Getter
public class TaskUserList {

    private List<TaskDto> tasks;
    private List<String> usernames;
}
