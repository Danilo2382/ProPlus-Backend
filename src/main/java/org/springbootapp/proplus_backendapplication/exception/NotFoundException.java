package org.springbootapp.proplus_backendapplication.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String property) {
        super(property + " not found.");
    }
}
