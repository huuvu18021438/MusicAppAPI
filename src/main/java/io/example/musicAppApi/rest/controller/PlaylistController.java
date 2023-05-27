package io.example.musicAppApi.rest.controller;

import io.example.musicAppApi.mapper.PlaylistMapper;
import io.example.musicAppApi.model.Playlist;
import io.example.musicAppApi.rest.api.NewOrUpdatePlaylistResource;
import io.example.musicAppApi.rest.api.PlaylistResource;
import io.example.musicAppApi.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/playlists")
@RequiredArgsConstructor
@Slf4j
public class PlaylistController {
    private final PlaylistService playlistService;
    private final PlaylistMapper playlistMapper;

    @GetMapping
    public Flux<PlaylistResource> getAll() {
        return playlistService.findAll().map(playlistMapper::toResource);
    }

    @GetMapping("/{id}")
    public Mono<PlaylistResource> findById(@PathVariable final Long id) {
        return playlistService.findById(id, true).map(playlistMapper::toResource);
    }

    @PostMapping
    public Mono<Playlist> create(@RequestBody final NewOrUpdatePlaylistResource newOrUpdatePlaylistResource) {
        return playlistService.create(playlistMapper.toModel(newOrUpdatePlaylistResource));
    }

    @PutMapping("/{id}")
    public Mono<Playlist> update(@PathVariable final Long id,
                                 @RequestBody final NewOrUpdatePlaylistResource newOrUpdatePlaylistResource) {
        return playlistService.findById(id, true)
                .map(playlist -> playlistMapper.toUpdate(newOrUpdatePlaylistResource, playlist))
                .flatMap(playlistService::update);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable final Long id) {
        return playlistService.deleteById(id);
    }

}
