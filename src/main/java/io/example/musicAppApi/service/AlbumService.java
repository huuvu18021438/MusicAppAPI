package io.example.musicAppApi.service;

import io.example.musicAppApi.model.Album;
import io.example.musicAppApi.repository.AlbumRepository;
import io.example.musicAppApi.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;

    public Flux<Album> findAll() {
        return albumRepository.findAll().flatMap(this::loadSongs);
    }

    public Mono<Album> findById(final Long id) {
        return albumRepository.findById(id).flatMap(this::loadSongs);
    }

    private Mono<Album> loadSongs(final Album album) {
        return Mono.just(album)
                .zipWith(songRepository.findSongsOfAlbumId(album.getId()).collectList())
                .map(result -> result.getT1().setSongs(result.getT2()));
    }
}
