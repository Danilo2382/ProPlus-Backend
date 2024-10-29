package org.springbootapp.proplus_backendapplication.services;

import lombok.RequiredArgsConstructor;
import org.springbootapp.proplus_backendapplication.exceptions.BadCredentialsException;
import org.springbootapp.proplus_backendapplication.exceptions.DataBaseException;
import org.springbootapp.proplus_backendapplication.exceptions.ResourceNotFoundException;
import org.springbootapp.proplus_backendapplication.model.Project;
import org.springbootapp.proplus_backendapplication.repositories.ProjectRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public List<Project> getAll() {
        try {
            return projectRepository.findAll();
        } catch (DataAccessException ex) {
            throw new DataBaseException(ex.getMessage());
        }
    }

    public Project getProjectById(Integer projectId) {
        try {
            return projectRepository.findById(projectId).orElseThrow(() -> new ResourceNotFoundException("Project with id " + projectId));
        } catch (DataAccessException ex) {
            throw new DataBaseException(ex.getMessage());
        }
    }

    public Project getProjectByCodeAndPassword(String code, String password) {
        try {
            return projectRepository.findByCodeAndPassword(code, password).orElseThrow(BadCredentialsException::new);
        } catch (DataAccessException ex) {
            throw new DataBaseException(ex.getMessage());
        }
    }

    public Project getProjectByIdAndPassword(Integer id, String password) {
        try {
            return projectRepository.findByIdAndPassword(id, password).orElseThrow(BadCredentialsException::new);
        } catch (DataAccessException ex) {
            throw new DataBaseException(ex.getMessage());
        }
    }

    public Project saveProject(Project project) {
        try {
            return projectRepository.save(project);
        } catch (DataAccessException ex) {
            throw new DataBaseException(ex.getMessage());
        }
    }

}
