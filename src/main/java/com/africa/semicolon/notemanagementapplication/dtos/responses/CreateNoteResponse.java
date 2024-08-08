package com.africa.semicolon.notemanagementapplication.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CreateNoteResponse {
    private String noteId;
    private String noteTitle;
    private String noteContent;
    private LocalDateTime dateCreated;
}
