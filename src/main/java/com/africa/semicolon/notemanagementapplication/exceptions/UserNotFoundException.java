package com.africa.semicolon.notemanagementapplication.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String response) {
        super(response);
    }
}
