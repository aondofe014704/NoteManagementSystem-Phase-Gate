package com.africa.semicolon.notemanagementapplication.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LogoutActorResponse {
    private String message;
    private boolean isLoggedIn;
}
