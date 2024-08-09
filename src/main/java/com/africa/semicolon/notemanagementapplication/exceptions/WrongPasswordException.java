package com.africa.semicolon.notemanagementapplication.exceptions;

public class WrongPasswordException extends NoteManagementApplicationException{
    public WrongPasswordException(String message) {
        super(message);
    }
}
