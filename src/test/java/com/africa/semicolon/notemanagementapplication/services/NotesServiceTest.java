package com.africa.semicolon.notemanagementapplication.services;

import com.africa.semicolon.notemanagementapplication.data.repositories.NotesRepository;
import com.africa.semicolon.notemanagementapplication.dtos.requests.CreateNoteRequest;
import com.africa.semicolon.notemanagementapplication.dtos.requests.UpdateNoteRequest;
import com.africa.semicolon.notemanagementapplication.dtos.responses.CreateNoteResponse;
import com.africa.semicolon.notemanagementapplication.dtos.responses.DeleteNoteResponse;
import com.africa.semicolon.notemanagementapplication.dtos.responses.UpdateNoteResponse;
import com.africa.semicolon.notemanagementapplication.exceptions.NoteTitleAlreadyExist;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
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
    @Autowired
    private NotesRepository notesRepository;

    @BeforeEach
    public void setUp() {
        notesRepository.deleteAll();
    }

    @Test
    public void testCreateNoteNewNote() {
        CreateNoteResponse createNoteResponse = createNewNoteRequest();
        assertThat(createNoteResponse).isNotNull();
        assertThat(createNoteResponse.getNoteId()).isNotNull();
        assertThat(notesService.getAllNotes()).size().isEqualTo(1L);

    }

    private CreateNoteResponse createNewNoteRequest() {
        CreateNoteRequest createNoteRequest = new CreateNoteRequest();
        createNoteRequest.setTitle("Woman called Mary Clark Panda");
        createNoteRequest.setContent("She is the famous African Queen");
        createNoteRequest.setAuthorEmail("songujack@gmail.com");
        return notesService.createNote(createNoteRequest);
    }

    @Test
    public void testCreateNoteWithSameTitle_ThrowsException() {
        CreateNoteResponse createNoteResponse = createNewNoteRequest();
        CreateNoteRequest createNoteRequest = new CreateNoteRequest();
        createNoteRequest.setTitle("Woman called Mary Clark Panda");
        createNoteRequest.setContent("new content");
        createNoteRequest.setAuthorEmail("songujack@gmail.com");
        assertThrows(NoteTitleAlreadyExist.class, () -> notesService.createNote(createNoteRequest));
    }
    @Test
    public void testToUpdateAnExistingNote(){
        CreateNoteResponse createNoteResponse = createNewNoteRequest();
        UpdateNoteRequest updateNoteRequest = new UpdateNoteRequest();
        updateNoteRequest.setId(createNoteResponse.getNoteId());
        updateNoteRequest.setNewTitle("Mary Clark The Love Of My Life");
        updateNoteRequest.setNewContent("The most beautiful girl in the world");
        UpdateNoteResponse updateNoteResponse1 = notesService.updateNote(updateNoteRequest);
        assertThat(updateNoteResponse1.getUpdatedTitle().contains("Love"));
        assertThat(updateNoteRequest.getId()).isEqualTo(updateNoteResponse1.getNoteId());
    }
    @Test
    public void testToDeleteExistingNote(){
        CreateNoteResponse createNoteResponse = createNewNoteRequest();
        String noteId = createNoteResponse.getNoteId();
        DeleteNoteResponse deleteNoteResponse1 = notesService.deleteNote(noteId);
        assertThat(deleteNoteResponse1).isNotNull();
        assertThat(deleteNoteResponse1.getMessage()).contains("deleted");
    }
//    @Test
//    public void testToShareNoteAmongActors(){
//        CreateNoteResponse createNoteResponse = createNewNoteRequest();
//        String noteId = createNoteResponse.getNoteId();
//        notesService.shareNoteAmongActors(noteId, "john@gmail.com", "jane@gmail.com");
//        assertThat(notesService.getNoteById(noteId).getActors()).contains("john@gmail.com", "jane@gmail.com");
//    }
}
@Test
public void testGetAllNotes(){
    CreateNoteResponse createNoteResponse = createNewNoteRequest();
    List<Note> notes = notesService.getAllNotes();
    assertThat(notes.size()).isEqualTo(1L);
}