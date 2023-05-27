package io.example.musicAppApi.repository;

import io.example.musicAppApi.model.SongPlaylist;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface SongPlaylistRepository extends R2dbcRepository<SongPlaylist, Long> {
    Flux<SongPlaylist> findAllByPlaylistId(Long playlistId);

    Mono<SongPlaylist> deleteAllByPlaylistId(Long playlistId);
}
