package com.africa.semicolon.notemanagementapplication.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginActorRequest {
    private String email;
    private String password;
}
