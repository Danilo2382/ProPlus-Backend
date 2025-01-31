package org.springbootapp.proplus_backendapplication.service;

import lombok.RequiredArgsConstructor;
import org.springbootapp.proplus_backendapplication.exception.NotFoundException;
import org.springbootapp.proplus_backendapplication.model.Project;
import org.springbootapp.proplus_backendapplication.model.ProjectMember;
import org.springbootapp.proplus_backendapplication.model.Task;
import org.springbootapp.proplus_backendapplication.model.User;
import org.springbootapp.proplus_backendapplication.repository.ProjectMemberRepository;
import org.springbootapp.proplus_backendapplication.repository.ProjectRepository;
import org.springbootapp.proplus_backendapplication.repository.TaskRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserActionsService {

    private final ProjectRepository projectRepository;
    private final UserInfoService userInfoService;
    private final ProjectMemberRepository projectMemberRepository;
    private final TaskRepository taskRepository;
    private final ValidationService validationService;

    @Transactional
    public Project createProject(String username, Project project) {
        User user = userInfoService.getUserByUsername(username);
        Project saved = projectRepository.save(project);
        saveProjectMember(user, saved, 2);
        return saved;
    }

    public Project joinProject(String username, String code, String password) {
        User user = userInfoService.getUserByUsername(username);
        Project project = projectRepository.findByCode(code).orElseThrow(() -> new NotFoundException("Project with code " + code));
        if (!project.getPassword().equals(password)) throw new BadCredentialsException("Invalid password.");
        saveProjectMember(user, project, 0);
        return project;
    }

    public Set<Project> getProjects(String username) {
        return projectRepository.findAllByProjectMembers_User_Username(username);
    }

    public Set<Task> getTasks(String username) {
        return taskRepository.findAllByProjectMember_User_Username(username);
    }

    private void saveProjectMember(User user, Project project, Integer role) {
        validationService.checkProjectMemberExists(user.getUsername(), project.getId());
        ProjectMember projectMember = new ProjectMember();
        projectMember.setUser(user);
        projectMember.setProject(project);
        projectMember.setRole(role);
        projectMemberRepository.save(projectMember);
    }
}
