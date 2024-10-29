package org.springbootapp.proplus_backendapplication.services;

import lombok.RequiredArgsConstructor;
import org.springbootapp.proplus_backendapplication.dtos.UserDto;
import org.springbootapp.proplus_backendapplication.exceptions.CredentialConflictException;
import org.springbootapp.proplus_backendapplication.exceptions.PasswordIncorrectException;
import org.springbootapp.proplus_backendapplication.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.springbootapp.proplus_backendapplication.formaters.UserFormater.formatDto;

@Service
@RequiredArgsConstructor
public class UserAuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public boolean login(String username, String password) {
        User user = userService.getUserByUsername(username, true);
        if (!passwordEncoder.matches(password, user.getPassword())) throw new PasswordIncorrectException();
        return true;
    }

    @Transactional
    public boolean register(UserDto userDto) {
        if (userService.getUserByUsername(userDto.getUsername(), false) != null)
            throw new CredentialConflictException(userDto.getUsername());
        if (userService.getUserByEmail(userDto.getEmail(), false) != null)
            throw new CredentialConflictException(userDto.getEmail());
        User user = formatDto(userDto);
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        user.setJoinDate(LocalDateTime.now());
        return userService.saveUser(user).getId() > 0;
    }
}
