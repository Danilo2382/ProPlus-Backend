package org.springbootapp.proplus_backendapplication.controllers;

import lombok.RequiredArgsConstructor;
import org.springbootapp.proplus_backendapplication.dtos.CommentDto;
import org.springbootapp.proplus_backendapplication.dtos.TaskDto;
import org.springbootapp.proplus_backendapplication.requests.CreateTaskRequest;
import org.springbootapp.proplus_backendapplication.services.TaskActionsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskActionsController {

    private final TaskActionsService taskActionsService;

    @GetMapping("/showComments")
    public ResponseEntity<List<CommentDto>> showComments(Principal principal, @RequestParam Integer taskId) {
        return ResponseEntity.ok().body(taskActionsService.showComments(principal.getName(), taskId));
    }

    @PostMapping("/createTask")
    public ResponseEntity<TaskDto> createTask(Principal principal, @RequestBody CreateTaskRequest request) {
        return ResponseEntity.ok().body(taskActionsService.createTask(principal.getName(), request.getIdProject(), request.getTask(), request.getUsername()));
    }

    @PutMapping("doTask")
    public ResponseEntity<TaskDto> doTask(Principal principal, @RequestParam Integer taskId, @RequestBody String text) {
        return ResponseEntity.ok().body(taskActionsService.doTask(principal.getName(), taskId, text));
    }

    @PutMapping("/finishTask")
    public ResponseEntity<TaskDto> finishTask(Principal principal, @RequestParam Integer taskId) {
        return ResponseEntity.ok().body(taskActionsService.finishTask(principal.getName(), taskId));
    }

    @PutMapping("/addComment")
    public ResponseEntity<CommentDto> addComment(Principal principal, @RequestParam Integer idTask, @RequestBody CommentDto commentDto) {
        return ResponseEntity.ok().body(taskActionsService.addComment(principal.getName(), idTask, commentDto));
    }
}
