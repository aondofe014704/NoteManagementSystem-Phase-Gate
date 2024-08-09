package com.africa.semicolon.notemanagementapplication.exceptions;

public class InvalidPasswordException extends NoteManagementApplicationException {
    public InvalidPasswordException(String response) {
        super(response);
    }
}
