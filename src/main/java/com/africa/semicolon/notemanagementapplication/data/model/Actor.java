package com.africa.semicolon.notemanagementapplication.data.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Actor {
    @Id
    private String id;
    private String username;
    private String email;
    private boolean isLoggedIn;
    private String phoneNumber;
    private String password;
    @DBRef
    private List<Notes> listOfNotes;
}
