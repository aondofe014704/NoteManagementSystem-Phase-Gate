package com.africa.semicolon.notemanagementapplication.services;

import com.africa.semicolon.notemanagementapplication.data.model.Actor;
import com.africa.semicolon.notemanagementapplication.data.repositories.ActorRepository;
import com.africa.semicolon.notemanagementapplication.dtos.requests.CreateNoteRequest;
import com.africa.semicolon.notemanagementapplication.dtos.requests.RegisterActorRequest;
import com.africa.semicolon.notemanagementapplication.dtos.responses.CreateNoteResponse;
import com.africa.semicolon.notemanagementapplication.dtos.responses.LoginActorResponse;
import com.africa.semicolon.notemanagementapplication.dtos.responses.LogoutActorResponse;
import com.africa.semicolon.notemanagementapplication.dtos.responses.RegisterActorResponse;
import com.africa.semicolon.notemanagementapplication.exceptions.EmailAlreadyExistsException;
import com.africa.semicolon.notemanagementapplication.exceptions.InvalidPasswordException;
import com.africa.semicolon.notemanagementapplication.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.africa.semicolon.notemanagementapplication.utils.Mapper.map;
import static com.africa.semicolon.notemanagementapplication.utils.Mapper.mapLogin;

@Service
public class ActorServiceImplementation implements ActorService {
    @Autowired
    private ActorRepository actorRepository;
    @Override
    public RegisterActorResponse register(RegisterActorRequest registerActorRequest) {
        boolean isEmailExisting = actorRepository.existsByEmail(registerActorRequest.getEmail());
        if(isEmailExisting){
            throw new EmailAlreadyExistsException("Email already exist");
        }
        Actor actor = new Actor();
        map(registerActorRequest, actor);
        actor.setPhoneNumber(registerActorRequest.getPhoneNumber());
        actorRepository.save(actor);
        return map(actor);
        }


    @Override
    public List<Actor> getAllUsers() {
        return actorRepository.findAll();
    }

    @Override
    public CreateNoteResponse createNote(CreateNoteRequest createNoteRequest) {
        return null;
    }

    @Override
    public LoginActorResponse login (com.africa.semicolon.notemanagementapplication.dtos.requests.LoginActorRequest loginActorRequest) {
        Actor actor = findByEmail(loginActorRequest.getEmail());
        validatePassword(actor.getPassword(),loginActorRequest.getPassword());
        actor.setLoggedIn(true);
        actorRepository.save(actor);
        return mapLogin(actor);
    }

    @Override
    public LogoutActorResponse logout(String email) {
        Actor actor = findByEmail(email);
        actor.setLoggedIn(false);
        actorRepository.save(actor);
        LogoutActorResponse logoutResponse = new LogoutActorResponse();
        logoutResponse.setMessage("user logged out successfully");
        logoutResponse.setLoggedIn(actor.isLoggedIn());
        return logoutResponse;
    }

    private Actor findByEmail(String email) {
        Actor actor =  actorRepository.findByEmail(email);
        if(actor == null) throw new UserNotFoundException("User not found");
        return actor;
    }
    private void validatePassword(String actorPassword, String password) {
        if(!password.matches(actorPassword)) throw new InvalidPasswordException("Invalid Details");
    }
}
