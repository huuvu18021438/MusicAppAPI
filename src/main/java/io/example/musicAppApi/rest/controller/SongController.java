package io.example.musicAppApi.rest.controller;

import io.example.musicAppApi.mapper.SongMapper;
import io.example.musicAppApi.rest.api.SongResource;
import io.example.musicAppApi.service.SongService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/songs")
@RequiredArgsConstructor
@Slf4j
public class SongController {
    private final SongService songService;
    private final SongMapper songMapper;

    @GetMapping
    public Flux<SongResource> getAll() {
        return songService.findAll().map(songMapper::toResource);
    }

    @GetMapping("/{id}")
    public Mono<SongResource> findById(@PathVariable final Long id) {
        return songService.findById(id).map(songMapper::toResource);
    }

    @GetMapping("/search")
    public Flux<SongResource> search(@RequestParam("name") String name) {
        return songService.search(name).map(songMapper::toResource);
    }
}
