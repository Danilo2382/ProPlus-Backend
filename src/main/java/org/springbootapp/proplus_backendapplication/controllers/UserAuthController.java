package org.springbootapp.proplus_backendapplication.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springbootapp.proplus_backendapplication.dtos.UserDto;
import org.springbootapp.proplus_backendapplication.requests.LoginRequest;
import org.springbootapp.proplus_backendapplication.services.UserAuthService;
import org.springbootapp.proplus_backendapplication.views.UserDtoView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserAuthController {

    private final UserAuthService userAuthService;

    @PostMapping("/login")
    public ResponseEntity<Boolean> loginUser(@RequestBody LoginRequest request) {
        return ResponseEntity.ok().body(userAuthService.login(request.getUsername(), request.getPassword()));
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> registerUser(@RequestBody @JsonView(value = UserDtoView.BasicAttributes.class) UserDto userDto) {
        return ResponseEntity.ok().body(userAuthService.register(userDto));
    }
}
