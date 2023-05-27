package io.example.musicAppApi.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("songs")
@Getter
@Setter
@ToString
@Accessors(chain = true)
@NoArgsConstructor
public class Song {
    @Id
    private Long id;

    private String name;

    private MusicGenre genre;

    private String author;

    private int releaseYear;

    private String singer;

    private int vote;

    private String lyric;

    @Column("image")
    private String imageUrl;

    private Long albumId;

    private Long fileId;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
