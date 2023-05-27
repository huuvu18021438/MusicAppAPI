package io.example.musicAppApi.mapper;

import io.example.musicAppApi.model.Song;
import io.example.musicAppApi.model.SongPlaylist;
import io.example.musicAppApi.rest.api.SongResource;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface SongMapper {
    SongResource toResource(Song song);

    default List<Song> toSongs(Collection<Long> songIds) {
        if (songIds == null) {
            return new ArrayList<>();
        }

        return songIds.stream()
                .map(songId -> new Song().setId(songId))
                .collect(Collectors.toList());
    }


    default Collection<Long> extractSongIdsFromSongs(Collection<Song> songs) {
        if (songs == null) {
            return new LinkedHashSet<>();
        }

        return songs.stream().map(Song::getId).collect(Collectors.toSet());
    }

    default Collection<Long> extractSongIdsFromSongPlaylists(Collection<SongPlaylist> songPlaylists) {
        if (songPlaylists == null) {
            return new LinkedHashSet<>();
        }

        return songPlaylists.stream().map(SongPlaylist::getSongId).collect(Collectors.toSet());
    }

    default Collection<SongPlaylist> toSongPlaylists(Long playlistId, Collection<Song> songs) {
        if (songs == null) {
            return new LinkedHashSet<>();
        }

        return songs.stream()
                .map(song -> new SongPlaylist(song.getId(), playlistId))
                .collect(Collectors.toSet());
    }
}
