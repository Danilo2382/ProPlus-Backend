package org.springbootapp.proplus_backendapplication.services;

import lombok.RequiredArgsConstructor;
import org.springbootapp.proplus_backendapplication.dtos.ProjectDto;
import org.springbootapp.proplus_backendapplication.exceptions.NoContentException;
import org.springbootapp.proplus_backendapplication.formaters.ProjectFormater;
import org.springbootapp.proplus_backendapplication.formaters.TaskFormater;
import org.springbootapp.proplus_backendapplication.model.Project;
import org.springbootapp.proplus_backendapplication.model.ProjectMember;
import org.springbootapp.proplus_backendapplication.model.Task;
import org.springbootapp.proplus_backendapplication.model.User;
import org.springbootapp.proplus_backendapplication.responses.ListResponse;
import org.springbootapp.proplus_backendapplication.responses.ProjectRoleList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springbootapp.proplus_backendapplication.converters.ProjectConverter.convertDto;
import static org.springbootapp.proplus_backendapplication.generators.ProjectCodeGenerator.*;

@Service
@RequiredArgsConstructor
public class UserActionsService {

    private final UserService userService;
    private final ProjectService projectService;
    private final ProjectMemberService projectMemberService;

    @Transactional
    public Map<String, String> createProject(String username, ProjectDto projectDto) {
        User user = userService.getUserByUsername(username, true);
        Project project = convertDto(projectDto);
        project.setCreateDate(LocalDateTime.now());
        project.setStatus(getStatusFromDate(project.getStartDate(), project.getEndDate()));
        project.setCode(generateUniqueCode(projectService.getAll().stream().map(Project::getCode).toList()));
        Project savedProject = projectService.saveProject(project);
        projectMemberService.saveProjectMember(savedProject, user, 2);
        Map<String, String> response = new HashMap<>();
        response.put("message", "The project has been successfully created");
        return response;
    }

    @Transactional
    public Map<String, String> joinProject(String username, String code, String password) {
        Project project = projectService.getProjectByCodeAndPassword(code, password);
        projectMemberService.saveProjectMember(project, userService.getUserByUsername(username, true), 0);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Successfully joined " + project.getName() + ".");
        return response;
    }

    @Transactional
    public ProjectRoleList getAllProjects(String username) {
        List<ProjectMember> projectMembers = projectMemberService.getAllByUser(userService.getUserByUsername(username, true));
        if (projectMembers.isEmpty()) throw new NoContentException("You aren't part of any project.");
        List<ProjectDto> projects = projectMembers.stream()
                .map(ProjectMember::getProjectIdproject)
                .map(ProjectFormater::formatDto)
                .toList();
        List<Integer> roles = projectMembers.stream()
                .map(ProjectMember::getRole)
                .toList();
        return new ProjectRoleList(projects, roles);
    }

    @Transactional
    public ListResponse getAllTasks(String username) {
        List<Task> tasks = projectMemberService.getAllByUser(userService.getUserByUsername(username, true)).stream()
                .flatMap(it -> it.getTasks().stream()).toList();
        if (tasks.isEmpty()) throw new NoContentException("You don't have any task in any project!");
        return new ListResponse(tasks.stream().map(TaskFormater::formatDto).toList(),
                "Getting tasks is successful!");
    }

}
