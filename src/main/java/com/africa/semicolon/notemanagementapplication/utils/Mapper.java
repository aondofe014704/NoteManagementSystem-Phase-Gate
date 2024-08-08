package com.africa.semicolon.notemanagementapplication.utils;

import com.africa.semicolon.notemanagementapplication.data.model.Actor;
import com.africa.semicolon.notemanagementapplication.data.model.Notes;
import com.africa.semicolon.notemanagementapplication.dtos.CreateNoteRequest;
import com.africa.semicolon.notemanagementapplication.dtos.requests.RegisterActorRequest;
import com.africa.semicolon.notemanagementapplication.dtos.responses.CreateNoteResponse;
import com.africa.semicolon.notemanagementapplication.dtos.responses.LoginActorResponse;
import com.africa.semicolon.notemanagementapplication.dtos.responses.RegisterActorResponse;

import java.time.LocalDateTime;

public class Mapper {
    public static void map(RegisterActorRequest registerActorRequest, Actor actor){
        actor.setEmail(registerActorRequest.getEmail());
        actor.setPassword(registerActorRequest.getPassword());
        actor.setPhoneNumber(registerActorRequest.getPhoneNumber());
        actor.setUsername(registerActorRequest.getUsername());
    }
    public static RegisterActorResponse map(Actor actor){
        RegisterActorResponse registerActorResponse = new RegisterActorResponse();
        registerActorResponse.setEmail(actor.getEmail());
        registerActorResponse.setMessage(actor.getEmail() + "Registered Successfully");
        return registerActorResponse;
    }
    public static LoginActorResponse mapLogin(Actor actor) {
        LoginActorResponse loginActorResponse = new LoginActorResponse();
        loginActorResponse.setEmail(actor.getEmail());
        loginActorResponse.setId(actor.getId());
        loginActorResponse.setMessage(actor.getEmail() + " logged in Successfully");
        loginActorResponse.setLoggedIn(actor.isLoggedIn());
        return loginActorResponse;
    }
    public static void map(CreateNoteRequest createNoteRequest, Notes notes){
        notes.setTitle(createNoteRequest.getTitle());
        notes.setContent(createNoteRequest.getContent());
        notes.setDateCreated(LocalDateTime.now());
    }
    public static CreateNoteResponse mapCreateResponse(Notes notes) {
        CreateNoteResponse createNoteResponse = new CreateNoteResponse();
        createNoteResponse.setNoteId(notes.getId());
        createNoteResponse.setNoteContent(notes.getContent());
        createNoteResponse.setNoteTitle(createNoteResponse.getNoteTitle());
        createNoteResponse.setDateCreated(LocalDateTime.now());
        return createNoteResponse;
    }
}
