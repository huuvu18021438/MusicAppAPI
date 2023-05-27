package io.example.musicAppApi.mapper;

import io.example.musicAppApi.model.Playlist;
import io.example.musicAppApi.rest.api.NewOrUpdatePlaylistResource;
import io.example.musicAppApi.rest.api.PlaylistResource;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {SongMapper.class})
public abstract class PlaylistMapper {
    @Autowired
    private SongMapper songMapper;

    public abstract PlaylistResource toResource(Playlist playlist);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "songs", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    public abstract Playlist toModel(NewOrUpdatePlaylistResource newOrUpdatePlaylistResource);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "songs", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    public abstract Playlist toUpdate(NewOrUpdatePlaylistResource newOrUpdatePlaylistResource, @MappingTarget Playlist playlist);

    @AfterMapping
    public void afterMapping(NewOrUpdatePlaylistResource newOrUpdatePlaylistResource, @MappingTarget Playlist playlist) {
        playlist.setSongs(songMapper.toSongs(newOrUpdatePlaylistResource.getSongIds()));
    }
}
