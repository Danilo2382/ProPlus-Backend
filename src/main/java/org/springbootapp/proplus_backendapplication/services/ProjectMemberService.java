package org.springbootapp.proplus_backendapplication.services;

import lombok.RequiredArgsConstructor;
import org.springbootapp.proplus_backendapplication.exceptions.*;
import org.springbootapp.proplus_backendapplication.model.Project;
import org.springbootapp.proplus_backendapplication.model.ProjectMember;
import org.springbootapp.proplus_backendapplication.model.User;
import org.springbootapp.proplus_backendapplication.repositories.ProjectMemberRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class ProjectMemberService {

    private final ProjectMemberRepository projectMemberRepository;

    public List<ProjectMember> getAllByUser(User user) {
        try {
            return projectMemberRepository.findAllByUserIduser(user).orElseThrow(() ->
                    new NoContentException("You aren't part of any project!"));
        } catch (DataAccessException ex) {
            throw new DataBaseException(ex.getMessage());
        }
    }

    public ProjectMember getByUserAndProject(User user, Project project) {
        try {
            return projectMemberRepository.findByUserIduserAndProjectIdproject(user, project).orElseThrow(() ->
                    new ResourceNotFoundException("Project member " + user.getUsername()));
        } catch (DataAccessException ex) {
            throw new DataBaseException(ex.getMessage());
        }
    }

    public Boolean checkProjectMember(User user, Project project) {
        try {
            return getByUserAndProject(user, project) != null;
        } catch (ResourceNotFoundException ex) {
            return false;
        }
    }

    public ProjectMember updateProjectMember(ProjectMember projectMember) {
        try {
            return projectMemberRepository.save(projectMember);
        } catch (DataAccessException ex) {
            throw new DataBaseException(ex.getMessage());
        }
    }

    public ProjectMember saveProjectMember(ProjectMember projectMember) {
        try {
            if (checkProjectMember(projectMember.getProjectIdproject(), projectMember.getUserIduser()))
                throw new ResourceConflictException("Project member " + projectMember.getUserIduser().getUsername());
            return projectMemberRepository.save(projectMember);
        } catch (DataAccessException ex) {
            throw new DataBaseException(ex.getMessage());
        }
    }

    public void saveProjectMember(Project project, User user, Integer role) {
        try {
            if (checkProjectMember(project, user))
                throw new ResourceConflictException("Project member " + user.getUsername());
            ProjectMember projectMember = new ProjectMember();
            projectMember.setProjectIdproject(project);
            projectMember.setUserIduser(user);
            projectMember.setRole(role);
            projectMemberRepository.save(projectMember);
        } catch (DataAccessException ex) {
            throw new DataBaseException(ex.getMessage());
        }
    }

    private Boolean checkProjectMember(Project project, User user) {
        try {
            return getAllByUser(user).stream().anyMatch(it -> Objects.equals(it.getProjectIdproject().getId(), project.getId()));
        } catch (NoContentException ex) {
            return false;
        } catch (DataAccessException ex) {
            throw new DataBaseException(ex.getMessage());
        }
    }

}
