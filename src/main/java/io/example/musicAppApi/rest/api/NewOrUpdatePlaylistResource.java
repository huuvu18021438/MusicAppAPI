package io.example.musicAppApi.rest.api;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@Accessors(chain = true)
public class NewOrUpdatePlaylistResource {
    private String name;

    private Set<Long> songIds;
}
