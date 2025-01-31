package org.springbootapp.proplus_backendapplication.service;

import lombok.RequiredArgsConstructor;
import org.springbootapp.proplus_backendapplication.exception.ConflictException;
import org.springbootapp.proplus_backendapplication.exception.NotAuthorizedException;
import org.springbootapp.proplus_backendapplication.repository.ProjectMemberRepository;
import org.springbootapp.proplus_backendapplication.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidationService {

    private final UserRepository userRepository;
    private final ProjectMemberRepository projectMemberRepository;

    public void checkUsernameExists(String username) {
        if (userRepository.existsByUsername(username)) throw new ConflictException("Username " + username);
    }

    public void checkEmailExists(String email) {
        if (userRepository.existsByEmail(email)) throw new ConflictException("Email " + email);
    }

    public void checkProjectMemberExists(String username, Long projectId) {
        if (projectMemberRepository.existsByUser_UsernameAndProject_Id(username, projectId)) throw new ConflictException("Project member " + username);
    }

    public void validateProjectMemberExists(String username, Long projectId) {
        if (!projectMemberRepository.existsByUser_UsernameAndProject_Id(username, projectId)) throw new NotAuthorizedException(username, projectId);
    }

    public void validateAdmin(Integer role) {
        if (role != 2) throw new NotAuthorizedException();
    }
}
