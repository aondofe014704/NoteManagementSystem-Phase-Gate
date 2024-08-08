package com.africa.semicolon.notemanagementapplication.exceptions;

public class NoteTitleAlreadyExist extends RuntimeException{
    public NoteTitleAlreadyExist(String message) {
        super(message);
    }
}
