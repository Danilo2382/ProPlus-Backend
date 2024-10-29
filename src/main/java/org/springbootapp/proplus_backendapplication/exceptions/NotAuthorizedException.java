package org.springbootapp.proplus_backendapplication.exceptions;

public class NotAuthorizedException extends RuntimeException {

    public NotAuthorizedException() {
        super("You don't have permission for this action!");
    }
}
