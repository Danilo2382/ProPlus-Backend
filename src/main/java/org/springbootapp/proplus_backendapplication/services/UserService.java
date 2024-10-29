package org.springbootapp.proplus_backendapplication.services;

import lombok.RequiredArgsConstructor;
import org.springbootapp.proplus_backendapplication.exceptions.DataBaseException;
import org.springbootapp.proplus_backendapplication.exceptions.ResourceNotFoundException;
import org.springbootapp.proplus_backendapplication.model.User;
import org.springbootapp.proplus_backendapplication.repositories.UserRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserByUsername(String username, Boolean throwException) {
        try {
            Optional<User> user = userRepository.findByUsername(username);
            if (throwException) return user.orElseThrow(() -> new ResourceNotFoundException("Username " + username));
            else return user.orElse(null);
        } catch (DataAccessException ex) {
            throw new DataBaseException(ex.getMessage());
        }
    }

    public User getUserByEmail(String email, Boolean throwException) {
        try {
            Optional<User> user = userRepository.findByEmail(email);
            if (throwException) return user.orElseThrow(() -> new ResourceNotFoundException("Email " + email));
            else return user.orElse(null);
        } catch (DataAccessException ex) {
            throw new DataBaseException(ex.getMessage());
        }
    }

    public User saveUser(User user) {
        try {
            return userRepository.save(user);
        } catch (DataAccessException ex) {
            throw new DataBaseException(ex.getMessage());
        }
    }

}
