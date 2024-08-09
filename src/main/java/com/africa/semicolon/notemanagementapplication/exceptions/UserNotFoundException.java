package com.africa.semicolon.notemanagementapplication.exceptions;

public class UserNotFoundException extends NoteManagementApplicationException{
    public UserNotFoundException(String response) {
        super(response);
    }
}
