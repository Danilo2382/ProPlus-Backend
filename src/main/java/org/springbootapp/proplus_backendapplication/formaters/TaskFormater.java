package org.springbootapp.proplus_backendapplication.formaters;

import org.springbootapp.proplus_backendapplication.dtos.TaskDto;
import org.springbootapp.proplus_backendapplication.model.Task;

public class TaskFormater {

    public static TaskDto formatDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getName(),
                task.getDescription(),
                task.getCreateDate(),
                task.getStatus()
        );
    }
}
