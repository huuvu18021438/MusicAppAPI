package io.example.musicAppApi.repository;

import io.example.musicAppApi.model.Song;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface SongRepository extends R2dbcRepository<Song, Long> {
    @Query("SELECT s.* FROM songs s JOIN songs_playlists sp ON s.id = sp.song_id WHERE sp.playlist_id = :playlist_id")
    Flux<Song> findSongsOfPlaylistId(Long playlistId);

    @Query("SELECT * FROM songs WHERE album_id = :albumId")
    Flux<Song> findSongsOfAlbumId(Long albumId);

    @Query("SElECT * FROM songs WHERE name LIKE CONCAT ('%', :name, '%')")
    Flux<Song> searchSongs(String name);
}
