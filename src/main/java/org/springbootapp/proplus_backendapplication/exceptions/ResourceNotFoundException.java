package org.springbootapp.proplus_backendapplication.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resource) {
        super(resource + " not found!");
    }

}