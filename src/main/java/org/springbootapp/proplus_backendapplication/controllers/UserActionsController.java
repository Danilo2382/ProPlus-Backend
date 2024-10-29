package org.springbootapp.proplus_backendapplication.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springbootapp.proplus_backendapplication.dtos.ProjectDto;
import org.springbootapp.proplus_backendapplication.requests.JoinProjectRequest;
import org.springbootapp.proplus_backendapplication.responses.ListResponse;
import org.springbootapp.proplus_backendapplication.responses.ProjectRoleList;
import org.springbootapp.proplus_backendapplication.services.UserActionsService;
import org.springbootapp.proplus_backendapplication.views.ProjectDtoView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/actions")
public class UserActionsController {

    private final UserActionsService userActionsService;

    @PostMapping("/createProject")
    public ResponseEntity<Map<String, String>> createProject(Principal principal,
                                                            @RequestBody @JsonView(value = ProjectDtoView.BasicAttributes.class) ProjectDto projectDto) {
        return ResponseEntity.ok().body(userActionsService.createProject(principal.getName(), projectDto));
    }

    @PostMapping("/joinProject")
    public ResponseEntity<Map<String, String>> joinProject(Principal principal, @RequestBody JoinProjectRequest request) {
        return ResponseEntity.ok().body(userActionsService.joinProject(principal.getName(), request.getCode(), request.getPassword()));
    }

    @GetMapping("/getAllProjects")
    public ResponseEntity<ProjectRoleList> getAllProjects(Principal principal) {
        return ResponseEntity.ok().body(userActionsService.getAllProjects(principal.getName()));
    }

    @GetMapping("/getAllTasks")
    public ResponseEntity<ListResponse> getAllTasks(Principal principal) {
        return ResponseEntity.ok().body(userActionsService.getAllTasks(principal.getName()));
    }

}
