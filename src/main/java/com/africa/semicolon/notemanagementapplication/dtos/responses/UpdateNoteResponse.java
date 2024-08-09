package com.africa.semicolon.notemanagementapplication.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class UpdateNoteResponse {
    private String noteId;
    private String updatedTitle;
    private String updatedContent;
    private LocalDateTime dateUpdated;
}
