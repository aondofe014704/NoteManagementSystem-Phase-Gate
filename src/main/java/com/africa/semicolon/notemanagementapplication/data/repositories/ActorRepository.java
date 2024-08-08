package com.africa.semicolon.notemanagementapplication.data.repositories;

import com.africa.semicolon.notemanagementapplication.data.model.Actor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActorRepository extends MongoRepository<Actor, String> {
    boolean existsByEmail(String email);

    Actor findByEmail(String email);
}
