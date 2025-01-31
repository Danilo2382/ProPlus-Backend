package org.springbootapp.proplus_backendapplication.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springbootapp.proplus_backendapplication.dto.request.ChangePropertyDto;
import org.springbootapp.proplus_backendapplication.dto.request.ChangeRoleDto;
import org.springbootapp.proplus_backendapplication.dto.response.MessageDto;
import org.springbootapp.proplus_backendapplication.dto.response.ProjectMemberUsernameDto;
import org.springbootapp.proplus_backendapplication.dto.response.TaskUsernameDto;
import org.springbootapp.proplus_backendapplication.model.Project;
import org.springbootapp.proplus_backendapplication.service.ProjectInfoService;
import org.springbootapp.proplus_backendapplication.validation.ValidProjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
@Validated
public class ProjectInfoController {

    private final ProjectInfoService projectInfoService;

    @GetMapping("/show")
    public ResponseEntity<Project> getProject(@RequestParam @ValidProjectId Long projectId, Principal principal) {
        return ResponseEntity.ok().body(projectInfoService.getProjectByUsernameAndId(principal.getName(), projectId));
    }

    @GetMapping("/members")
    public ResponseEntity<Set<ProjectMemberUsernameDto>> getMembers(@RequestParam @ValidProjectId Long projectId, Principal principal) {
        return ResponseEntity.ok().body(projectInfoService.listMembers(principal.getName(), projectId));
    }

    @GetMapping("/tasks")
    public ResponseEntity<Set<TaskUsernameDto>> getTasks(@RequestParam @ValidProjectId Long projectId, Principal principal) {
        return ResponseEntity.ok().body(projectInfoService.listTasks(principal.getName(), projectId));
    }

    @PatchMapping("/changePassword")
    public ResponseEntity<MessageDto> changePassword(@RequestParam @ValidProjectId Long projectId, @RequestBody @Validated(ChangePropertyDto.ChangePassword.class) ChangePropertyDto body, Principal principal) {
        return ResponseEntity.ok().body(projectInfoService.changePassword(principal.getName(), projectId, body.property()));
    }

    @PatchMapping("/changeRole")
    public ResponseEntity<MessageDto> changeMemberRole(@RequestParam @ValidProjectId Long projectId, @RequestBody @Valid ChangeRoleDto body, Principal principal) {
        return ResponseEntity.ok().body(projectInfoService.changeRole(principal.getName(), projectId, body.username(), body.role()));
    }

}
