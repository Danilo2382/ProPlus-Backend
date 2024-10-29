package org.springbootapp.proplus_backendapplication.exceptions;

public class BadCredentialsException extends RuntimeException {

    public BadCredentialsException() {
        super("Credentials are incorrect!");
    }
}
