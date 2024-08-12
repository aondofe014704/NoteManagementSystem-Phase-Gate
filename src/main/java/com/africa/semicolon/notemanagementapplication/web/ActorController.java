package com.africa.semicolon.notemanagementapplication.web;

import com.africa.semicolon.notemanagementapplication.dtos.requests.LoginActorRequest;
import com.africa.semicolon.notemanagementapplication.dtos.requests.RegisterActorRequest;
import com.africa.semicolon.notemanagementapplication.dtos.responses.ApiResponse;
import com.africa.semicolon.notemanagementapplication.dtos.responses.LoginActorResponse;
import com.africa.semicolon.notemanagementapplication.dtos.responses.RegisterActorResponse;
import com.africa.semicolon.notemanagementapplication.services.ActorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/actor")
@AllArgsConstructor
public class ActorController {
    private ActorService actorService;

    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(@RequestBody RegisterActorRequest registerActorRequest) {
        try {
            RegisterActorResponse registerActorResponse = actorService.register(registerActorRequest);
            return new ResponseEntity<>(new ApiResponse(true,registerActorResponse), CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
        }
    }
    @PostMapping("/login-user")
    public ResponseEntity<?> loginUser(@RequestBody LoginActorRequest loginActorRequest){
        try{
            LoginActorResponse  loginActorResponse = actorService.login(loginActorRequest);
            return new ResponseEntity<>(new ApiResponse(true, loginActorResponse), CREATED);
        }
        catch (Exception exception){
            return new ResponseEntity<>(new ApiResponse(false, exception), BAD_REQUEST);
        }
    }
}
