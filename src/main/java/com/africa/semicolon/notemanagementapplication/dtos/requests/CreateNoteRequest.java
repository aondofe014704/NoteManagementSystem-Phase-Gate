package com.africa.semicolon.notemanagementapplication.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateNoteRequest {
    private String authorEmail;
    private String title;
    private String content;
}
