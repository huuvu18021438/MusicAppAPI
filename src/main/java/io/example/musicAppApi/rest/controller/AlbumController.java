package io.example.musicAppApi.rest.controller;

import io.example.musicAppApi.mapper.AlbumMapper;
import io.example.musicAppApi.rest.api.AlbumResource;
import io.example.musicAppApi.service.AlbumService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/albums")
@RequiredArgsConstructor
@Slf4j
public class AlbumController {
    private final AlbumService albumService;
    private final AlbumMapper albumMapper;

    @GetMapping
    public Flux<AlbumResource> getAll() {
        return albumService.findAll().map(albumMapper::toResource);
    }

    @GetMapping("/{id}")
    public Mono<AlbumResource> findById(@PathVariable final Long id) {
        return albumService.findById(id).map(albumMapper::toResource);
    }
}
