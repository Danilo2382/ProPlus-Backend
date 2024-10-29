package org.springbootapp.proplus_backendapplication.converters;

import org.springbootapp.proplus_backendapplication.dtos.TaskDto;
import org.springbootapp.proplus_backendapplication.model.Task;

public class TaskConverter {

    public static Task convertDto(TaskDto taskDto) {
        Task task = new Task();
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        return task;
    }
}
