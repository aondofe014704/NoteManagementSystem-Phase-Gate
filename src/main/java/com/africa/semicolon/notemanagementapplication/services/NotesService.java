package com.africa.semicolon.notemanagementapplication.services;

import com.africa.semicolon.notemanagementapplication.data.model.Notes;
import com.africa.semicolon.notemanagementapplication.dtos.requests.CreateNoteRequest;
import com.africa.semicolon.notemanagementapplication.dtos.requests.UpdateNoteRequest;
import com.africa.semicolon.notemanagementapplication.dtos.responses.CreateNoteResponse;
import com.africa.semicolon.notemanagementapplication.dtos.responses.DeleteNoteResponse;
import com.africa.semicolon.notemanagementapplication.dtos.responses.UpdateNoteResponse;

import java.util.List;

public interface NotesService {
    CreateNoteResponse createNote (CreateNoteRequest CreateNoteRequest);
    UpdateNoteResponse updateNote(UpdateNoteRequest updateNoteRequest);

    List<Notes> getAllNotes();

    DeleteNoteResponse deleteNote(String noteId);
}
