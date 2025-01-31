package org.springbootapp.proplus_backendapplication.controller;

import lombok.RequiredArgsConstructor;
import org.springbootapp.proplus_backendapplication.model.User;
import org.springbootapp.proplus_backendapplication.dto.request.ChangePropertyDto;
import org.springbootapp.proplus_backendapplication.dto.response.MessageDto;
import org.springbootapp.proplus_backendapplication.service.UserInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserInfoController {

    private final UserInfoService userInfoService;

    @GetMapping("/show")
    public ResponseEntity<User> getUser(Principal principal) {
        return ResponseEntity.ok().body(userInfoService.getUserByUsername(principal.getName()));
    }

    @PatchMapping("/changeUsername")
    public ResponseEntity<MessageDto> changeUsername(@RequestBody @Validated(ChangePropertyDto.ChangeUsername.class) ChangePropertyDto body, Principal principal) {
        return ResponseEntity.ok().body(userInfoService.changeUsername(principal.getName(), body.property()));
    }

    @PatchMapping("/changeEmail")
    public ResponseEntity<MessageDto> changeEmail(@RequestBody @Validated(ChangePropertyDto.ChangeEmail.class) ChangePropertyDto body, Principal principal) {
        return ResponseEntity.ok().body(userInfoService.changeEmail(principal.getName(), body.property()));
    }

    @PatchMapping("/changePassword")
    public ResponseEntity<MessageDto> changePassword(@RequestBody @Validated(ChangePropertyDto.ChangePassword.class) ChangePropertyDto body, Principal principal) {
        return ResponseEntity.ok().body(userInfoService.changePassword(principal.getName(), body.property()));
    }
}
