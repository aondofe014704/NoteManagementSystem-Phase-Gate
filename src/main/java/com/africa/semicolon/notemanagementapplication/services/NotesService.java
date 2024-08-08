package com.africa.semicolon.notemanagementapplication.services;

import com.africa.semicolon.notemanagementapplication.data.model.Notes;
import com.africa.semicolon.notemanagementapplication.dtos.CreateNoteRequest;
import com.africa.semicolon.notemanagementapplication.dtos.responses.CreateNoteResponse;

import java.util.List;

public interface NotesService {

    CreateNoteResponse createNote (CreateNoteRequest CreateNoteRequest);

    List<Notes> getAllNotes();
}
