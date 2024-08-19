package com.africa.semicolon.notemanagementapplication.services;

import com.africa.semicolon.notemanagementapplication.data.repositories.ActorRepository;
import com.africa.semicolon.notemanagementapplication.dtos.requests.RegisterActorRequest;
import com.africa.semicolon.notemanagementapplication.dtos.responses.LoginActorResponse;
import com.africa.semicolon.notemanagementapplication.dtos.responses.LogoutActorResponse;
import com.africa.semicolon.notemanagementapplication.dtos.responses.RegisterActorResponse;
import com.africa.semicolon.notemanagementapplication.exceptions.EmailAlreadyExistsException;
import com.africa.semicolon.notemanagementapplication.exceptions.InvalidPasswordException;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.CrossOrigin;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ActorServiceTest {
    @Autowired
    private ActorService actorService;
    @Autowired
    private ActorRepository userRepository;


    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }
    @Test
    public void testToRegisterUser(){
        RegisterActorRequest registerActorRequest = registerActorRequest();
        RegisterActorResponse registerActorResponse = actorService.register(registerActorRequest);
        assertThat(registerActorResponse).isNotNull();
        assertThat(actorService.getAllUsers().size()).isEqualTo(1L);
    }

    private static RegisterActorRequest registerActorRequest() {
        RegisterActorRequest registerActorRequest = new RegisterActorRequest();
        registerActorRequest.setEmail("songujack@gmail.com");
        registerActorRequest.setUsername("JackSongu014704");
        registerActorRequest.setPassword("1234567");
        registerActorRequest.setPhoneNumber("08133608698");
        return registerActorRequest;
    }
    @Test
    public void testToNotRegisterWithSameEmailThrowException() {
        actorService.register(registerActorRequest());
        RegisterActorRequest registerActorRequest = new RegisterActorRequest();
        registerActorRequest.setEmail("songujack@gmail.com");
        registerActorRequest.setUsername("JackSongu014704");
        registerActorRequest.setPassword("1234567");
        registerActorRequest.setPhoneNumber("08133608698");
        assertThrows(EmailAlreadyExistsException.class, () -> actorService.register(registerActorRequest));
    }
    @Test
    public void testToLoginToLoginAnActor(){
        actorService.register(registerActorRequest());
        com.africa.semicolon.notemanagementapplication.dtos.requests.LoginActorRequest loginActorRequest = new com.africa.semicolon.notemanagementapplication.dtos.requests.LoginActorRequest();
        loginActorRequest.setEmail("songujack@gmail.com");
        loginActorRequest.setPassword("1234567");
        LoginActorResponse loginActorResponse = actorService.login(loginActorRequest);
        assertThat(loginActorResponse).isNotNull();
        assertThat(loginActorResponse.isLoggedIn()).isTrue();
    }
    @Test
    public void testToLoginWithWrongPassword_ThrowException(){
        actorService.register(registerActorRequest());
        com.africa.semicolon.notemanagementapplication.dtos.requests.LoginActorRequest loginActorRequest = new com.africa.semicolon.notemanagementapplication.dtos.requests.LoginActorRequest();
        loginActorRequest.setEmail("songujack@gmail.com");
        loginActorRequest.setPassword("wrongPassword");
        assertThrows(InvalidPasswordException.class, () -> actorService.login(loginActorRequest));
    }
    @Test
    public void testToLogOut(){
        actorService.register(registerActorRequest());
        com.africa.semicolon.notemanagementapplication.dtos.requests.LoginActorRequest loginActorRequest = new com.africa.semicolon.notemanagementapplication.dtos.requests.LoginActorRequest();
        loginActorRequest.setEmail("songujack@gmail.com");
        loginActorRequest.setPassword("1234567");
        actorService.login(loginActorRequest);
        LogoutActorResponse logoutActorResponse = actorService.logout(loginActorRequest.getEmail());
        assertThat(logoutActorResponse).isNotNull();
        assertThat(logoutActorResponse.getMessage()).contains("successfully");
    }

}