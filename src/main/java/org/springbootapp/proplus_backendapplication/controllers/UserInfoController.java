package org.springbootapp.proplus_backendapplication.controllers;

import lombok.RequiredArgsConstructor;
import org.springbootapp.proplus_backendapplication.dtos.UserDto;
import org.springbootapp.proplus_backendapplication.requests.UserInfoRequest;
import org.springbootapp.proplus_backendapplication.responses.UserInfoResponse;
import org.springbootapp.proplus_backendapplication.services.UserInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserInfoController {

    private final UserInfoService userInfoService;

    @GetMapping("/showDetails")
    public ResponseEntity<UserDto> showDetails(Principal principal) {
        return ResponseEntity.ok().body(userInfoService.showDetails(principal.getName()));
    }

    @PutMapping("/changeUsername")
    public ResponseEntity<UserInfoResponse> changeUsername(Principal principal, @RequestBody UserInfoRequest request) {
        return ResponseEntity.ok().body(userInfoService.changeUsername(principal.getName(), request.getInfo()));
    }

    @PutMapping("/changeEmail")
    public ResponseEntity<UserInfoResponse> changeEmail(Principal principal, @RequestBody UserInfoRequest request) {
        return ResponseEntity.ok().body(userInfoService.changeEmail(principal.getName(), request.getInfo()));
    }

    @PutMapping("/changePassword")
    public ResponseEntity<UserInfoResponse> changePassword(Principal principal, @RequestBody UserInfoRequest request) {
        return ResponseEntity.ok().body(userInfoService.changePassword(principal.getName(), request.getInfo()));
    }
}
