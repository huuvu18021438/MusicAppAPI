package io.example.musicAppApi.service;

import io.example.musicAppApi.model.Song;
import io.example.musicAppApi.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SongService {
    private final SongRepository songRepository;

    public Flux<Song> findAll() {
        return songRepository.findAll();
    }

    public Mono<Song> findById(Long id) {
        return songRepository.findById(id);
    }

    public Flux<Song> search(String name) {
        return songRepository.searchSongs(name);
    }
}
