package org.springbootapp.proplus_backendapplication.services;

import lombok.RequiredArgsConstructor;
import org.springbootapp.proplus_backendapplication.dtos.ProjectDto;
import org.springbootapp.proplus_backendapplication.dtos.ProjectMemberDto;
import org.springbootapp.proplus_backendapplication.exceptions.NotAuthorizedException;
import org.springbootapp.proplus_backendapplication.formaters.TaskFormater;
import org.springbootapp.proplus_backendapplication.model.Project;
import org.springbootapp.proplus_backendapplication.model.ProjectMember;
import org.springbootapp.proplus_backendapplication.model.Task;
import org.springbootapp.proplus_backendapplication.model.User;
import org.springbootapp.proplus_backendapplication.responses.ProjectInfoResponse;
import org.springbootapp.proplus_backendapplication.responses.TaskUserList;
import org.springbootapp.proplus_backendapplication.responses.UsernameRoleList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Set;

import static org.springbootapp.proplus_backendapplication.formaters.ProjectFormater.formatDto;

@Service
@RequiredArgsConstructor
public class ProjectInfoService {

    private final UserService userService;
    private final ProjectService projectService;
    private final ProjectMemberService projectMemberService;

    @Transactional
    public ProjectDto showDetails(String username, Integer projectId) {
        if (projectMemberService.getByUserAndProject(userService.getUserByUsername(username, true),
                projectService.getProjectById(projectId)) == null)
            throw new NotAuthorizedException();
        return formatDto(projectService.getProjectById(projectId));
    }

    @Transactional
    public UsernameRoleList listMembers(String username, Integer projectId) {
        if (projectMemberService.getByUserAndProject(userService.getUserByUsername(username, true),
                projectService.getProjectById(projectId)) == null)
            throw new NotAuthorizedException();
        Set<ProjectMember> projectMembers = projectService.getProjectById(projectId).getProjectmembers();
        return new UsernameRoleList(
                projectMembers.stream().map(it -> it.getUserIduser().getUsername()).toList(),
                projectMembers.stream().map(ProjectMember::getRole).toList()
        );
    }

    @Transactional
    public TaskUserList listTasks(String username, Integer projectId) {
        if (projectMemberService.getByUserAndProject(userService.getUserByUsername(username, true),
                projectService.getProjectById(projectId)) == null)
            throw new NotAuthorizedException();
        Set<Task> tasks = projectService.getProjectById(projectId).getTasks();
        return new TaskUserList(
                tasks.stream()
                        .map(TaskFormater::formatDto).toList(),
                tasks.stream()
                        .map(Task::getProjectmemberIdprojectmember)
                        .map(ProjectMember::getUserIduser)
                    .map(User::getUsername).toList());
    }

    @Transactional
    public ProjectInfoResponse changePassword(String username, Integer code, String password, String newPassword) {
        Project project = projectService.getProjectByIdAndPassword(code, password);
        ProjectMember projectmember = projectMemberService.getByUserAndProject
                (userService.getUserByUsername(username, true), project);
        if (projectmember == null || projectmember.getRole() != 2) throw new NotAuthorizedException();
        project.setPassword(newPassword);
        return new ProjectInfoResponse(formatDto(projectService.saveProject(project)),
                null,
                "The password has been successfully changed!");
    }

    @Transactional
    public ProjectInfoResponse changeRole(String adminUsername, Integer projectId, String memberUsername, Integer newRole) {
        Project project = projectService.getProjectById(projectId);
        ProjectMember projectMember = projectMemberService.getByUserAndProject(
                userService.getUserByUsername(adminUsername, true), project);
        if (projectMember == null || projectMember.getRole() != 2) throw new NotAuthorizedException();
        User user = userService.getUserByUsername(memberUsername, true);
        projectMember = projectMemberService.getByUserAndProject(user, project);
        projectMember.setRole(newRole);
        return new ProjectInfoResponse(formatDto(project),
                new ProjectMemberDto(projectMemberService.updateProjectMember(projectMember).getRole()),
                "The role has been changed successfully");
    }
}
