package org.springbootapp.proplus_backendapplication.exceptions;

public class ResourceConflictException extends RuntimeException {

    public ResourceConflictException(String resource) {
        super(resource + " already exists!");
    }
}
