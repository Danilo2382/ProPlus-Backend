package org.springbootapp.proplus_backendapplication.exception;

public class ConflictException extends RuntimeException{

    public ConflictException(String property) {
        super(property + " already exists.");
    }
}
