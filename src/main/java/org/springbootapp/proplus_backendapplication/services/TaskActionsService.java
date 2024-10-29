package org.springbootapp.proplus_backendapplication.services;

import lombok.RequiredArgsConstructor;
import org.springbootapp.proplus_backendapplication.dtos.CommentDto;
import org.springbootapp.proplus_backendapplication.dtos.TaskDto;
import org.springbootapp.proplus_backendapplication.exceptions.NotAuthorizedException;
import org.springbootapp.proplus_backendapplication.formaters.CommentFormater;
import org.springbootapp.proplus_backendapplication.model.*;
import org.springframework.stereotype.Service;
import static org.springbootapp.proplus_backendapplication.converters.TaskConverter.convertDto;
import static org.springbootapp.proplus_backendapplication.formaters.TaskFormater.formatDto;
import static org.springbootapp.proplus_backendapplication.formaters.CommentFormater.formatDto;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskActionsService {

    private final UserService userService;
    private final ProjectService projectService;
    private final ProjectMemberService projectMemberService;
    private final TaskService taskService;
    private final CommentService commentService;

    public List<CommentDto> showComments(String username, Integer idTask) {
        Task task = taskService.getTaskById(idTask);
        if (projectMemberService.checkProjectMember(
                userService.getUserByUsername(username, true), task.getProjectIdproject()))
            throw new NotAuthorizedException();
        return task.getComments().stream().map(CommentFormater::formatDto).toList();
    }

    public TaskDto createTask(String username, Integer projectId, TaskDto taskDto, String usernameMember) {
        ProjectMember projectMember = projectMemberService.getByUserAndProject(
                userService.getUserByUsername(username, true), projectService.getProjectById(projectId));
        if (projectMember.getRole() != 2 && projectMember.getRole() != 1) throw new NotAuthorizedException();
        Task task = convertDto(taskDto);
        task.setCreateDate(LocalDateTime.now());
        task.setStatus(0);
        task.setProjectmemberIdprojectmember(projectMemberService.getByUserAndProject(
                userService.getUserByUsername(usernameMember, true), projectMember.getProjectIdproject()));
        task.setProjectIdproject(projectMember.getProjectIdproject());
        return formatDto(taskService.saveTask(task));
    }

    public TaskDto doTask(String username, Integer taskId, String text) {
        Task task = taskService.getTaskById(taskId);
        if (projectMemberService.checkProjectMember(
                userService.getUserByUsername(username, true), task.getProjectIdproject()))
            throw new NotAuthorizedException();
        task.setDescription(task.getDescription() + "\n" + text);
        return formatDto(taskService.saveTask(task));
    }

    public TaskDto finishTask(String username, Integer idTask) {
        Task task = taskService.getTaskById(idTask);
        if (!projectMemberService.checkProjectMember(
                userService.getUserByUsername(username, true), task.getProjectIdproject()))
            throw new NotAuthorizedException();
        task.setStatus(1);
        return formatDto(taskService.saveTask(task));
    }

    public CommentDto addComment(String username, Integer idTask, CommentDto commentDto) {
        Task task = taskService.getTaskById(idTask);
        ProjectMember projectMember = projectMemberService.getByUserAndProject(
                userService.getUserByUsername(username, true), task.getProjectIdproject());
        Comment comment = new Comment();
        comment.setText(commentDto.getText());
        comment.setTimeStamp(commentDto.getTimeStamp());
        comment.setProjectmemberIdprojectmember(projectMember);
        comment.setTaskIdtask(task);
        return formatDto(commentService.saveComment(comment));
    }
}
