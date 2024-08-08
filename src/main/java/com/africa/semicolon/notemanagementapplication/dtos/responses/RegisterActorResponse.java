package com.africa.semicolon.notemanagementapplication.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterActorResponse {
    private String id;
    private String message;
    private String email;
}
