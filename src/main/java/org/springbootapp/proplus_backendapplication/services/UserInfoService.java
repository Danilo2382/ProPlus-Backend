package org.springbootapp.proplus_backendapplication.services;

import lombok.RequiredArgsConstructor;
import static org.springbootapp.proplus_backendapplication.converters.UserConverter.convertUser;

import org.springbootapp.proplus_backendapplication.dtos.UserDto;
import org.springbootapp.proplus_backendapplication.exceptions.CredentialConflictException;
import org.springbootapp.proplus_backendapplication.model.User;
import org.springbootapp.proplus_backendapplication.responses.UserInfoResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDto showDetails(String username) {
        return convertUser(userService.getUserByUsername(username, true));
    }

    @Transactional
    public UserInfoResponse changeUsername(String username, String newUsername) {
        User user = userService.getUserByUsername(username, true);
        if (userService.getUserByUsername(newUsername, false) != null) {
            throw new CredentialConflictException(newUsername);
        }
        user.setUsername(newUsername);
        return new UserInfoResponse(convertUser(userService.saveUser(user)),
                "Username has been successfully changed!");
    }

    @Transactional
    public UserInfoResponse changeEmail(String username, String newEmail) {
        User user = userService.getUserByUsername(username, true);
        if (userService.getUserByEmail(newEmail, false) != null) {
            throw new CredentialConflictException(newEmail);
        }
        user.setEmail(newEmail);
        return new UserInfoResponse(convertUser(userService.saveUser(user)),
                "Email has been successfully changed!");
    }

    @Transactional
    public UserInfoResponse changePassword(String username, String newPassword) {
        User user = userService.getUserByUsername(username, true);
        user.setPassword(passwordEncoder.encode(newPassword));
        return new UserInfoResponse(convertUser(userService.saveUser(user)),
                "Password has been successfully changed!");
    }
}
