package org.springbootapp.proplus_backendapplication.exceptions;

public class CredentialConflictException extends RuntimeException {

    public CredentialConflictException(String credential) {
        super(credential + " is already taken!");
    }
}
