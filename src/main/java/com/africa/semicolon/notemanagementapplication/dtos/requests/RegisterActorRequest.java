package com.africa.semicolon.notemanagementapplication.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterActorRequest {
    private String email;
    private String password;
    private String username;
    private String phoneNumber;
}
