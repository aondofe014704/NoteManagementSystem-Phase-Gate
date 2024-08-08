package com.africa.semicolon.notemanagementapplication.services;

import com.africa.semicolon.notemanagementapplication.data.model.Notes;
import com.africa.semicolon.notemanagementapplication.data.repositories.NotesRepository;
import com.africa.semicolon.notemanagementapplication.dtos.CreateNoteRequest;
import com.africa.semicolon.notemanagementapplication.dtos.responses.CreateNoteResponse;
import com.africa.semicolon.notemanagementapplication.exceptions.NoteTitleAlreadyExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.africa.semicolon.notemanagementapplication.utils.Mapper.mapCreateResponse;

@Service
public class NotesServiceImplementation implements NotesService{
    @Autowired
    private NotesRepository notesRepository;
    @Override
    public CreateNoteResponse createNote(CreateNoteRequest createNoteRequest) {
        validateTitle(createNoteRequest.getTitle());
        Notes notes = new Notes();
        mapCreateResponse(notes);
        notesRepository.save(notes);
        return mapCreateResponse(notes);
    }
    private void validateTitle(String title){
        boolean existsByTitle = notesRepository.existsByTitle(title);
        if(existsByTitle) throw new NoteTitleAlreadyExist(title + " already exists");
    }

    @Override
    public List<Notes> getAllNotes() {
        return notesRepository.findAll();
    }
}
