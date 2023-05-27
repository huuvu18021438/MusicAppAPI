package io.example.musicAppApi.repository;

import io.example.musicAppApi.model.Playlist;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends R2dbcRepository<Playlist, Long> {
}
