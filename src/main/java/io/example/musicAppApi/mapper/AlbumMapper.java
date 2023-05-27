package io.example.musicAppApi.mapper;

import io.example.musicAppApi.model.Album;
import io.example.musicAppApi.rest.api.AlbumResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = SongMapper.class)
public abstract class AlbumMapper {
    public abstract AlbumResource toResource(Album album);
}
