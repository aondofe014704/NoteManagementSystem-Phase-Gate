package com.africa.semicolon.notemanagementapplication.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginActorResponse {
    private String email;
    private String message;
    private boolean isLoggedIn;
    private String id;
}
