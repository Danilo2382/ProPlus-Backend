package org.springbootapp.proplus_backendapplication.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springbootapp.proplus_backendapplication.dto.request.UserDto;
import org.springbootapp.proplus_backendapplication.dto.request.LoginDto;
import org.springbootapp.proplus_backendapplication.dto.response.JwtDto;
import org.springbootapp.proplus_backendapplication.dto.response.MessageDto;
import org.springbootapp.proplus_backendapplication.service.UserAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserAuthController {

    private final UserAuthService userAuthService;

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@RequestBody @Valid LoginDto body) {
        return ResponseEntity.ok(userAuthService.login(body.username(), body.password()));
    }

    @PostMapping("/register")
    public ResponseEntity<MessageDto> register(@RequestBody @Valid UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userAuthService.register(userDto.convertToUser()));
    }
}
