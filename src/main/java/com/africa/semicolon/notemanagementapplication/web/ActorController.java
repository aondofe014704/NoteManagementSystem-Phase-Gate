package com.africa.semicolon.notemanagementapplication.web;

import com.africa.semicolon.notemanagementapplication.data.model.Actor;
import com.africa.semicolon.notemanagementapplication.dtos.requests.LoginActorRequest;
import com.africa.semicolon.notemanagementapplication.dtos.requests.RegisterActorRequest;
import com.africa.semicolon.notemanagementapplication.dtos.responses.ApiResponse;
import com.africa.semicolon.notemanagementapplication.dtos.responses.LoginActorResponse;
import com.africa.semicolon.notemanagementapplication.dtos.responses.LogoutActorResponse;
import com.africa.semicolon.notemanagementapplication.dtos.responses.RegisterActorResponse;
import com.africa.semicolon.notemanagementapplication.services.ActorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/actor")
@AllArgsConstructor
@CrossOrigin(origins = "*")
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
    @PostMapping("/api/v1/logout-user")
    public ResponseEntity <?> logout (@PathVariable String email){
        try {
            LogoutActorResponse logoutActorResponse = actorService.logout(email);
            return new ResponseEntity<>(new ApiResponse(true, logoutActorResponse), CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false, e), BAD_REQUEST);
        }
    }
    @GetMapping("/api/v1/get-All-Users")
    public ResponseEntity<?> getAllUsers(){
        try {
            List<Actor> actor = actorService.getAllUsers();
            return new ResponseEntity<>(actor, OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);
        }
    }
}
