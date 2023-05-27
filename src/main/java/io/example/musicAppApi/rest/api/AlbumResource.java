package io.example.musicAppApi.rest.api;

import io.example.musicAppApi.model.Song;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.List;

@Data
@Accessors(chain = true)
public class AlbumResource {
    private Long id;

    private String name;

    private String description;

    private List<Song> songs;
}
