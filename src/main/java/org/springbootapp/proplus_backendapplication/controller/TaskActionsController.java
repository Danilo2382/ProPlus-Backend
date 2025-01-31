package org.springbootapp.proplus_backendapplication.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springbootapp.proplus_backendapplication.dto.request.CommentDto;
import org.springbootapp.proplus_backendapplication.dto.request.CreateTaskDto;
import org.springbootapp.proplus_backendapplication.dto.response.MessageDto;
import org.springbootapp.proplus_backendapplication.model.Comment;
import org.springbootapp.proplus_backendapplication.model.Task;
import org.springbootapp.proplus_backendapplication.service.TaskActionsService;
import org.springbootapp.proplus_backendapplication.validation.ValidProjectId;
import org.springbootapp.proplus_backendapplication.validation.ValidTaskId;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
@Validated
public class TaskActionsController {

    private final TaskActionsService taskActionsService;

    @GetMapping("/comments")
    public ResponseEntity<Set<Comment>> getComments(@RequestParam @ValidTaskId Long taskId, Principal principal) {
        return ResponseEntity.ok().body(taskActionsService.showComments(principal.getName(), taskId));
    }

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestParam @ValidProjectId Long projectId, @RequestBody @Valid CreateTaskDto body, Principal principal) {
        return ResponseEntity.ok().body(taskActionsService.createTask(principal.getName(), projectId, body.username(), body.taskDto().convertToTask()));
    }

    @PatchMapping("/finish")
    public ResponseEntity<MessageDto> finishTask(@RequestParam @ValidTaskId Long taskId, Principal principal) {
        return ResponseEntity.ok().body(taskActionsService.finishTask(principal.getName(), taskId));
    }

    @PostMapping("/addComment")
    public ResponseEntity<Comment> createComment(@RequestParam @ValidTaskId Long taskId, @RequestBody @Valid CommentDto body, Principal principal) {
        return ResponseEntity.ok().body(taskActionsService.createComment(principal.getName(), taskId, body.text()));
    }
}
