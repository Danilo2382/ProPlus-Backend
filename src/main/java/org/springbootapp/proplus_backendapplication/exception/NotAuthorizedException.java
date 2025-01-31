package org.springbootapp.proplus_backendapplication.exception;

public class NotAuthorizedException extends RuntimeException {

    public NotAuthorizedException() {
        super("You don't have permission for this action");
    }

    public NotAuthorizedException(String username, Long projectId) {
        super("User " + username + " is not member of project with ID " + projectId);
    }
}
