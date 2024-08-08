package com.africa.semicolon.notemanagementapplication.services;

import com.africa.semicolon.notemanagementapplication.data.model.Actor;
import com.africa.semicolon.notemanagementapplication.dtos.requests.LoginActorRequest;
import com.africa.semicolon.notemanagementapplication.dtos.requests.RegisterActorRequest;
import com.africa.semicolon.notemanagementapplication.dtos.responses.LoginActorResponse;
import com.africa.semicolon.notemanagementapplication.dtos.responses.RegisterActorResponse;

import java.util.Collection;
import java.util.List;

public interface ActorService {
    RegisterActorResponse register (RegisterActorRequest registerActorRequest);

   List<Actor> getAllUsers();

    LoginActorResponse login(LoginActorRequest loginActorRequest);
}
