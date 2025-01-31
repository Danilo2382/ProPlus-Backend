package org.springbootapp.proplus_backendapplication.service;

import lombok.RequiredArgsConstructor;
import org.springbootapp.proplus_backendapplication.dto.response.MessageDto;
import org.springbootapp.proplus_backendapplication.exception.NotAuthorizedException;
import org.springbootapp.proplus_backendapplication.model.*;
import org.springbootapp.proplus_backendapplication.repository.CommentRepository;
import org.springbootapp.proplus_backendapplication.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class TaskActionsService {

    private final TaskRepository taskRepository;
    private final ValidationService validationService;
    private final ProjectInfoService projectInfoService;
    private final CommentRepository commentRepository;

    public Set<Comment> showComments(String username, Long taskId) {
        Task task = taskRepository.findTaskById(taskId);
        validationService.validateProjectMemberExists(username, task.getProjectMember().getProject().getId());
        return task.getComments();
    }

    public Task createTask(String username, Long projectId, String memberUsername, Task task) {
        validationService.validateAdmin(projectInfoService.getProjectMemberByUsernameAndProjectId(username, projectId).getRole());
        task.setProjectMember(projectInfoService.getProjectMemberByUsernameAndProjectId(memberUsername, projectId));
        return taskRepository.save(task);
    }

    public MessageDto finishTask(String username, Long taskId) {
        Task task = getTaskByUsernameAndId(username, taskId);
        task.setStatus(1);
        return new MessageDto("Task finished");
    }

    public Comment createComment(String username, Long taskId, String text) {
        Task task = taskRepository.findTaskById(taskId);
        ProjectMember projectMember = projectInfoService.getProjectMemberByUsernameAndProjectId(username, task.getProjectMember().getProject().getId());
        Comment comment = new Comment();
        comment.setText(text);
        comment.setProjectMember(projectMember);
        comment.setTask(task);
        return commentRepository.save(comment);
    }

    private Task getTaskByUsernameAndId(String username, Long taskId) {
        return taskRepository.findByProjectMember_User_UsernameAndId(username, taskId).orElseThrow(NotAuthorizedException::new);
    }
}
