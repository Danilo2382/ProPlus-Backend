package org.springbootapp.proplus_backendapplication.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springbootapp.proplus_backendapplication.dto.request.ProjectDto;
import org.springbootapp.proplus_backendapplication.model.Project;
import org.springbootapp.proplus_backendapplication.model.Task;
import org.springbootapp.proplus_backendapplication.dto.request.JoinProjectDto;
import org.springbootapp.proplus_backendapplication.service.UserActionsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/action")
@RequiredArgsConstructor
public class UserActionsController {

    private final UserActionsService userActionsService;

    @GetMapping("/projects")
    public ResponseEntity<Set<Project>> getProjects(Principal principal) {
        return ResponseEntity.ok().body(userActionsService.getProjects(principal.getName()));
    }

    @GetMapping("/tasks")
    public ResponseEntity<Set<Task>> getTasks(Principal principal) {
        return ResponseEntity.ok().body(userActionsService.getTasks(principal.getName()));
    }

    @PostMapping("/createProject")
    public ResponseEntity<Project> createProject(@RequestBody @Valid ProjectDto projectDto, Principal principal) {
        return ResponseEntity.ok().body(userActionsService.createProject(principal.getName(), projectDto.convertToProject()));
    }

    @PostMapping("/joinProject")
    public ResponseEntity<Project> joinProject(@RequestBody @Valid JoinProjectDto body, Principal principal) {
        return ResponseEntity.ok().body(userActionsService.joinProject(principal.getName(), body.code(), body.password()));
    }

}
