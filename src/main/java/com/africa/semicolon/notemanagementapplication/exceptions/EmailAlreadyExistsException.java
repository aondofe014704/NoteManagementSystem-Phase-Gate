package com.africa.semicolon.notemanagementapplication.exceptions;

public class EmailAlreadyExistsException extends NoteManagementApplicationException{
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
