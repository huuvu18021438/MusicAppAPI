package io.example.musicAppApi.repository;

import io.example.musicAppApi.model.Album;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends R2dbcRepository<Album, Long> {
}
