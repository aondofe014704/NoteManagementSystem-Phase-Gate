package com.africa.semicolon.notemanagementapplication.exceptions;

public class NoteNotFoundException extends NoteManagementApplicationException {
    public NoteNotFoundException(String response) {
        super(response);
    }
}
