package org.springbootapp.proplus_backendapplication.service;

import lombok.RequiredArgsConstructor;
import org.springbootapp.proplus_backendapplication.dto.response.MessageDto;
import org.springbootapp.proplus_backendapplication.dto.response.ProjectMemberUsernameDto;
import org.springbootapp.proplus_backendapplication.dto.response.TaskUsernameDto;
import org.springbootapp.proplus_backendapplication.exception.NotAuthorizedException;
import org.springbootapp.proplus_backendapplication.exception.NotFoundException;
import org.springbootapp.proplus_backendapplication.model.Project;
import org.springbootapp.proplus_backendapplication.model.ProjectMember;
import org.springbootapp.proplus_backendapplication.repository.ProjectMemberRepository;
import org.springbootapp.proplus_backendapplication.repository.ProjectRepository;
import org.springbootapp.proplus_backendapplication.repository.TaskRepository;
import org.springframework.stereotype.Service;


import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProjectInfoService {

    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final TaskRepository taskRepository;
    private final ValidationService validationService;

    public Project getProjectByUsernameAndId(String username, Long projectId) {
        return projectRepository.findByProjectMembers_User_UsernameAndId(username, projectId).orElseThrow(() ->
                new NotAuthorizedException(username, projectId));
    }

    public Set<ProjectMemberUsernameDto> listMembers(String username, Long projectId) {
        validationService.validateProjectMemberExists(username, projectId);
        return projectMemberRepository.findAllWithUsernameByProject_Id(projectId);
    }

    public Set<TaskUsernameDto> listTasks(String username, Long projectId) {
        validationService.validateProjectMemberExists(username, projectId);
        return taskRepository.findAllWithUsernameByProject_Id(projectId);
    }

    public MessageDto changePassword(String username, Long projectId, String newPassword) {
        ProjectMember admin = getProjectMemberByUsernameAndProjectId(username, projectId);
        validationService.validateAdmin(admin.getRole());
        Project project = admin.getProject();
        project.setPassword(newPassword);
        projectRepository.save(project);
        return new MessageDto("Password has been successfully changed.");
    }

    public MessageDto changeRole(String username, Long projectId, String memberUsername, Integer newRole) {
        ProjectMember admin = getProjectMemberByUsernameAndProjectId(username, projectId);
        validationService.validateAdmin(admin.getRole());
        ProjectMember member = admin.getProject().getProjectMembers()
                .stream()
                .filter(it -> it.getUser().getUsername().equals(memberUsername))
                .findFirst().orElseThrow(() -> new NotFoundException("Project Member " + memberUsername));
        member.setRole(newRole);
        projectMemberRepository.save(member);
        return new MessageDto("Role has been successfully changed.");
    }

    public ProjectMember getProjectMemberByUsernameAndProjectId(String username, Long projectId) {
        return projectMemberRepository.findByUser_UsernameAndProject_Id(username, projectId).orElseThrow(() ->
                new NotAuthorizedException(username, projectId));
    }

}
