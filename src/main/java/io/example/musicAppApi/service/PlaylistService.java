package io.example.musicAppApi.service;

import io.example.musicAppApi.exception.PlaylistNotFoundException;
import io.example.musicAppApi.mapper.SongMapper;
import io.example.musicAppApi.model.Playlist;
import io.example.musicAppApi.model.SongPlaylist;
import io.example.musicAppApi.repository.PlaylistRepository;
import io.example.musicAppApi.repository.SongPlaylistRepository;
import io.example.musicAppApi.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final SongRepository songRepository;
    private final SongPlaylistRepository songPlaylistRepository;
    private final SongMapper songMapper;

    public Flux<Playlist> findAll() {
        return playlistRepository.findAll().flatMap(this::loadSongs);
    }


    @Transactional
    public Mono<Playlist> create(Playlist playlist) {
        if (playlist.getId() != null) {
            return Mono.error(new IllegalArgumentException("When creating an playlist, the id must be null"));
        }

        return playlistRepository.save(playlist)
                .flatMap(savedPlaylist ->
                        songPlaylistRepository.saveAll(songMapper.toSongPlaylists(savedPlaylist.getId(), savedPlaylist.getSongs()))
                                .collectList()
                                .then(Mono.just(savedPlaylist)));
    }

    @Transactional
    public Mono<Playlist> update(Playlist playlistToSave) {
        if (playlistToSave.getId() == null) {
            return Mono.error(new IllegalArgumentException("When updating a playlist, the id must be provided"));
        }

        return verifyExistence(playlistToSave.getId())
                .then(songPlaylistRepository.findAllByPlaylistId(playlistToSave.getId()).collectList())
                .flatMap(currentSongPlaylists -> {
                    final Collection<Long> existSongIds = songMapper.extractSongIdsFromSongPlaylists(currentSongPlaylists);
                    final Collection<Long> songIdsToSave = songMapper.extractSongIdsFromSongs(playlistToSave.getSongs());

                    final Collection<SongPlaylist> removedSongPlaylists = currentSongPlaylists.stream()
                            .filter(songPlaylist -> !songIdsToSave.contains(songPlaylist.getSongId()))
                            .collect(Collectors.toList());

                    final Collection<SongPlaylist> addedSongPlaylists = songIdsToSave.stream()
                            .filter(songId -> !existSongIds.contains(songId))
                            .map(songId -> new SongPlaylist(songId, playlistToSave.getId()))
                            .collect(Collectors.toList());

                    return songPlaylistRepository.deleteAll(removedSongPlaylists)
                            .then(songPlaylistRepository.saveAll(addedSongPlaylists).collectList());

                })
                .then(playlistRepository.save(playlistToSave));
    }

    public Mono<Void> deleteById(final Long id) {
        return findById(id, false)
                .zipWith(songPlaylistRepository.deleteAllByPlaylistId(id))
                .map(Tuple2::getT1)
                .flatMap(playlistRepository::delete);
    }

    public Mono<Playlist> findById(final Long id, final Boolean loadSongs) {
        Mono<Playlist> playlistMono = playlistRepository.findById(id);
        return loadSongs ? playlistMono.flatMap(this::loadSongs) : playlistMono;
    }

    private Mono<Playlist> loadSongs(final Playlist playlist) {
        return Mono.just(playlist)
                .zipWith(songRepository.findSongsOfPlaylistId(playlist.getId()).collectList())
                .map(result -> result.getT1().setSongs(result.getT2()));
    }

    private Mono<Boolean> verifyExistence(final Long id) {
        return playlistRepository.existsById(id).handle((exists, sink) -> {
            if (Boolean.FALSE.equals(exists)) {
                sink.error(new PlaylistNotFoundException(id));
            } else {
                sink.next(exists);
            }
        });
    }
}
