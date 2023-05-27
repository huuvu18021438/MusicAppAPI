package io.example.musicAppApi.exception;

public class PlaylistNotFoundException extends NotFoundException{
    public PlaylistNotFoundException(Long id) {
        super(String.format("Playlist [%d] is not found", id));
    }
}
