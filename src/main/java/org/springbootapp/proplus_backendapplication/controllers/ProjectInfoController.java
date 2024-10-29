package org.springbootapp.proplus_backendapplication.controllers;

import lombok.RequiredArgsConstructor;
import org.springbootapp.proplus_backendapplication.dtos.ProjectDto;
import org.springbootapp.proplus_backendapplication.requests.ProjectInfoRequest;
import org.springbootapp.proplus_backendapplication.responses.ProjectInfoResponse;
import org.springbootapp.proplus_backendapplication.responses.TaskUserList;
import org.springbootapp.proplus_backendapplication.responses.UsernameRoleList;
import org.springbootapp.proplus_backendapplication.services.ProjectInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project")
public class ProjectInfoController {

    private final ProjectInfoService projectInfoService;

    @GetMapping("/showDetails")
    public ResponseEntity<ProjectDto> showDetails(Principal principal, @RequestParam Integer id) {
        return ResponseEntity.ok().body(projectInfoService.showDetails(principal.getName(), id));
    }

    @GetMapping("/listMembers")
    public ResponseEntity<UsernameRoleList> listMembers(Principal principal, @RequestParam Integer id) {
        return ResponseEntity.ok().body(projectInfoService.listMembers(principal.getName(), id));
    }

    @GetMapping("/listTasks")
    public ResponseEntity<TaskUserList> listTasks(Principal principal, @RequestParam Integer id) {
        return ResponseEntity.ok().body(projectInfoService.listTasks(principal.getName(), id));
    }

    @PutMapping("/changePassword")
    public ResponseEntity<?> changePassword(Principal principal, @RequestBody ProjectInfoRequest request) {
        return ResponseEntity.ok().body(projectInfoService.changePassword(principal.getName(), request.getId(),
                request.getParam1(), request.getParam2()));
    }

    @PutMapping("/changeRole")
    public ResponseEntity<ProjectInfoResponse> changeMemberRole(Principal principal, @RequestBody ProjectInfoRequest request) {
        return ResponseEntity.ok().body(projectInfoService.changeRole(principal.getName(), request.getId(),
                request.getParam1(), Integer.parseInt(request.getParam2())));
    }

}
