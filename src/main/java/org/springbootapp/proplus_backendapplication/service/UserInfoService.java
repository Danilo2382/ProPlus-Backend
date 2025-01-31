package org.springbootapp.proplus_backendapplication.service;

import lombok.RequiredArgsConstructor;

import org.springbootapp.proplus_backendapplication.model.User;
import org.springbootapp.proplus_backendapplication.repository.UserRepository;
import org.springbootapp.proplus_backendapplication.dto.response.MessageDto;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserRepository userRepository;
    private final ValidationService validationService;
    private final PasswordEncoder passwordEncoder;

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found"));
    }

    public MessageDto changeUsername(String username, String newUsername) {
        validationService.checkUsernameExists(newUsername);
        User user = getUserByUsername(username);
        user.setUsername(newUsername);
        userRepository.save(user);
        return new MessageDto("Username has been successfully changed.");
    }

    public MessageDto changeEmail(String username, String newEmail) {
        validationService.checkEmailExists(newEmail);
        User user = getUserByUsername(username);
        user.setEmail(newEmail);
        userRepository.save(user);
        return new MessageDto("Email has been successfully changed.");
    }

    public MessageDto changePassword(String username, String newPassword) {
        User user = getUserByUsername(username);
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return new MessageDto("Password has been successfully changed.");
    }
}
