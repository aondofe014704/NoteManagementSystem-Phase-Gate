package com.africa.semicolon.notemanagementapplication.exceptions;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String response) {
        super(response);
    }
}
