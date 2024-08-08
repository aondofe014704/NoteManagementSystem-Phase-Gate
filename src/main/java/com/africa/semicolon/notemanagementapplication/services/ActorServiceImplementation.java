package com.africa.semicolon.notemanagementapplication.services;

import com.africa.semicolon.notemanagementapplication.data.model.Actor;
import com.africa.semicolon.notemanagementapplication.data.repositories.ActorRepository;
import com.africa.semicolon.notemanagementapplication.dtos.requests.LoginActorRequest;
import com.africa.semicolon.notemanagementapplication.dtos.requests.RegisterActorRequest;
import com.africa.semicolon.notemanagementapplication.dtos.responses.LoginActorResponse;
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
    public LoginActorResponse login(LoginActorRequest loginActorRequest) {
        Actor actor = findByEmail(loginActorRequest.getEmail());
        validatePassword(loginActorRequest.getPassword());
        actor.setLoggedIn(true);
        actorRepository.save(actor);
        return mapLogin(actor);
    }
    private Actor findByEmail(String email) {
        Actor actor =  actorRepository.findByEmail(email);
        if(actor == null) throw new UserNotFoundException("User not found");
        return actor;
    }
    private void validatePassword(String password) {
        if(!password.matches(password)) throw new InvalidPasswordException("Invalid Details");
    }
}
