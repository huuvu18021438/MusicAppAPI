package io.example.musicAppApi.repository;

import io.example.musicAppApi.model.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends R2dbcRepository<User, String> {
    @Query("SELECT * FROM users WHERE username = :username")
    Mono<User> findByUsername(String username);
}
