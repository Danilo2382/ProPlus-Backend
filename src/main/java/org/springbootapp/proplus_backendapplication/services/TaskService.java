package org.springbootapp.proplus_backendapplication.services;

import lombok.RequiredArgsConstructor;
import org.springbootapp.proplus_backendapplication.exceptions.DataBaseException;
import org.springbootapp.proplus_backendapplication.exceptions.ResourceNotFoundException;
import org.springbootapp.proplus_backendapplication.model.Task;
import org.springbootapp.proplus_backendapplication.repositories.TaskRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Task getTaskById(Integer id) {
        try {
            return taskRepository.findTaskById(id).orElseThrow(() -> new ResourceNotFoundException("Task"));
        } catch (DataAccessException ex) {
            throw new DataBaseException(ex.getMessage());
        }
    }

    public Task saveTask(Task task) {
        try {
            return taskRepository.save(task);
        } catch (DataAccessException ex) {
            throw new DataBaseException(ex.getMessage());
        }
    }
}
