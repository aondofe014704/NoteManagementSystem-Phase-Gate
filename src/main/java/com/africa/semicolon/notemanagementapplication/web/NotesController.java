package com.africa.semicolon.notemanagementapplication.web;

import com.africa.semicolon.notemanagementapplication.dtos.requests.CreateNoteRequest;
import com.africa.semicolon.notemanagementapplication.dtos.requests.UpdateNoteRequest;
import com.africa.semicolon.notemanagementapplication.dtos.responses.ApiResponse;
import com.africa.semicolon.notemanagementapplication.dtos.responses.CreateNoteResponse;
import com.africa.semicolon.notemanagementapplication.dtos.responses.UpdateNoteResponse;
import com.africa.semicolon.notemanagementapplication.services.NotesService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/notes")
@RequiredArgsConstructor
public class NotesController {
    private final NotesService notesService;

    @PostMapping("/create-note")
    public ResponseEntity<?> createNote(@RequestBody CreateNoteRequest createNoteRequest){
        try{
            CreateNoteResponse createNoteResponse = notesService.createNote(createNoteRequest);
            return  new ResponseEntity<>(new ApiResponse (true, createNoteResponse), CREATED);
        }catch (Exception exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), BAD_REQUEST);
        }
    }
    @PatchMapping("/update-note")
    public ResponseEntity<?> updateNote(@RequestBody UpdateNoteRequest updateNoteRequest){
        try{
            UpdateNoteResponse updateNoteResponse = notesService.updateNote(updateNoteRequest);
            return new ResponseEntity<>(new ApiResponse(true, updateNoteResponse),OK);
        }catch(Exception exception){
            return new ResponseEntity<>(new ApiResponse(false,exception.getMessage()), BAD_REQUEST);
        }
    }
}
