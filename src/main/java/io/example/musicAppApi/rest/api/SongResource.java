package io.example.musicAppApi.rest.api;

import io.example.musicAppApi.model.MusicGenre;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class SongResource {
    private Long id;

    private String name;

    private MusicGenre genre;

    private String author;

    private int releaseYear;

    private String singer;

    private int vote;

    private String lyric;

    private String imageUrl;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;
}
