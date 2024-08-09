package com.africa.semicolon.notemanagementapplication.services;

import com.africa.semicolon.notemanagementapplication.data.model.Notes;
import com.africa.semicolon.notemanagementapplication.data.repositories.NotesRepository;
import com.africa.semicolon.notemanagementapplication.dtos.requests.CreateNoteRequest;
import com.africa.semicolon.notemanagementapplication.dtos.requests.UpdateNoteRequest;
import com.africa.semicolon.notemanagementapplication.dtos.responses.DeleteNoteResponse;
import com.africa.semicolon.notemanagementapplication.dtos.responses.UpdateNoteResponse;
import com.africa.semicolon.notemanagementapplication.exceptions.NoteNotFoundException;
import com.africa.semicolon.notemanagementapplication.exceptions.NoteTitleAlreadyExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.africa.semicolon.notemanagementapplication.utils.Mapper.mapCreateNoteResponse;
import static com.africa.semicolon.notemanagementapplication.utils.Mapper.mapUpdateNoteResponse;

@Service
public class NotesServiceImplementation implements NotesService{
    @Autowired
    private NotesRepository notesRepository;
    @Override
    public com.africa.semicolon.notemanagementapplication.dtos.responses.CreateNoteResponse createNote(CreateNoteRequest createNoteRequest) {
        validateTitle(createNoteRequest.getTitle());
        Notes notes = new Notes();
        notes.setTitle(createNoteRequest.getTitle());
        notes.setContent(createNoteRequest.getContent());
        mapCreateNoteResponse(notes);
        notesRepository.save(notes);
        return mapCreateNoteResponse(notes);
    }

    @Override
    public UpdateNoteResponse updateNote(UpdateNoteRequest updateNoteRequest) {
            Notes notes = findById(updateNoteRequest.getId());
            notes.setTitle(updateNoteRequest.getNewTitle());
            notes.setContent(updateNoteRequest.getNewContent());
            notes.setDateModified(java.time.LocalDateTime.now());
            notesRepository.save(notes);
            return mapUpdateNoteResponse(notes);
    }

    private Notes findById(String id) {
        return notesRepository.findById(id)
               .orElseThrow(() -> new NoteNotFoundException("Note not found"));
    }

    private void validateTitle(String title){
        boolean existsByTitle = notesRepository.existsByTitle(title);
        if(existsByTitle) throw new NoteTitleAlreadyExist(title + " already exists");
    }

    @Override
    public List<Notes> getAllNotes() {
        return notesRepository.findAll();
    }

    @Override
    public DeleteNoteResponse deleteNote(String noteId) {
        Notes notes = findById(noteId);
        notesRepository.delete(notes);
        DeleteNoteResponse deleteNoteResponse = new DeleteNoteResponse();
        deleteNoteResponse.setMessage("Note deleted successfully");
        return deleteNoteResponse;

    }

}

