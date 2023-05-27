INSERT INTO albums (name, description)
VALUES('Easy On Me', 'Album nhac dong que');

INSERT INTO files(name, type) VALUES ('EasyOnMeMusicFile', 'Binary');

INSERT INTO songs (name, genre, author, release_year, singer, vote, album_id, file_id)
 VALUES
 ('Easy on Me', 'Pop', 'Adele', 2021, 'Adele', 100, 1, 1),
 ('Real Friends', 'Rock', 'Camila', 2020, 'Camila', 200, 1, 1)
 ('Happy New Year', 'Country', 'ABBA', 2000, 'ABBA', 1000, 1, 1);

 INSERT INTO playlists (name) VALUES ('Favorite songs');

INSERT INTO songs_playlists(song_id, playlist_id) VALUES (2, 1), (3, 1), (4, 1);