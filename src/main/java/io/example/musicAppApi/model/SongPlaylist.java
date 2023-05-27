package io.example.musicAppApi.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("songs_playlists")
@Getter
@Setter
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class SongPlaylist {

    public SongPlaylist(Long songId, Long playlistId) {
        this.songId = songId;
        this.playlistId = playlistId;
    }

    @Id
    private Long id;

    private Long songId;

    private Long playlistId;
}
