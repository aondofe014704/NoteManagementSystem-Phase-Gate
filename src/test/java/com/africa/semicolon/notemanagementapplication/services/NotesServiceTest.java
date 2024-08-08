package com.africa.semicolon.notemanagementapplication.services;

import com.africa.semicolon.notemanagementapplication.dtos.CreateNoteRequest;
import com.africa.semicolon.notemanagementapplication.dtos.responses.CreateNoteResponse;
import com.africa.semicolon.notemanagementapplication.exceptions.NoteTitleAlreadyExist;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor
public class NotesServiceTest {
    @Autowired
    private NotesService notesService;
    @Test
    public void testCreateNote(){
        CreateNoteRequest createNoteRequest = createNoteRequest();
        CreateNoteResponse createNoteResponse = notesService.createNote(createNoteRequest);
        assertThat(createNoteResponse).isNotNull();
        assertThat(createNoteResponse.getNoteId()).isNotNull();
        assertThat(notesService.getAllNotes()).size().isEqualTo(1L);

    }

    private static CreateNoteRequest createNoteRequest() {
        CreateNoteRequest createNoteRequest = new CreateNoteRequest();
        createNoteRequest.setTitle("The Woman called Mary Clark Panda");
        createNoteRequest.setContent("She is the famous African Queen");
        createNoteRequest.setAuthorEmail("songujack@gmail.com");
        return createNoteRequest;
    }
    @Test
    public void testToCreateNoteWithSametitle_ThrowsException(){
        CreateNoteRequest createNoteRequest = createNoteRequest();
        notesService.createNote(createNoteRequest);
        CreateNoteRequest createNoteRequest2 = new CreateNoteRequest();
        createNoteRequest2.setTitle("The Woman called Mary Clark Panda");
        createNoteRequest2.setContent("She is the famous African Queen");
        createNoteRequest2.setAuthorEmail("songujack@gmail.com");
        assertThrows(NoteTitleAlreadyExist.class, () -> notesService.createNote(createNoteRequest2));
    }


}