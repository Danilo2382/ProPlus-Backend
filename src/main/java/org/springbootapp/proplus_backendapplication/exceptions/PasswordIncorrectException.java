package org.springbootapp.proplus_backendapplication.exceptions;

public class PasswordIncorrectException extends RuntimeException {

    public PasswordIncorrectException() {
        super("Password is incorrect.");
    }
}
