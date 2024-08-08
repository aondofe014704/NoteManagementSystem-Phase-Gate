package com.africa.semicolon.notemanagementapplication.data.repositories;

import com.africa.semicolon.notemanagementapplication.data.model.Notes;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotesRepository extends MongoRepository<Notes, String> {
    boolean existsByTitle(String title);
}
