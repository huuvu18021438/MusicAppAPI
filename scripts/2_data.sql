NSERT INTO albums (name, author, description)
VALUES('Easy On Me', 'Adele', 'Album nhac dong que');

INSERT INTO files(name, type) VALUES ('EasyOnMeMusicFile', 'Binary');

INSERT INTO songs (name, genre, author, release_year, singer, vote, album_id, file_id)
 VALUES
 ('Easy on Me', 'Pop', 'Adele', 2021, 'Adele', 100, 1, 1),
 ('Real Friends', 'Rock', 'Camila', 2020, 'Camila', 200, 1, 1)
 ('Happy New Year', 'Country', 'ABBA', 2000, 'ABBA', 1000, 1, 1);

INSERT INTO users (username, password, user_role, first_name, last_name, email)
VALUES ('huuvu', '$2a$10$esolmUvFZDqSIE744dU5V.5dPxBk0.xzjDXe7Gim4tou7DXYBLa4q', 'USER', 'Vu', 'Huu', 'huuvu@gmail.com');

INSERT INTO playlists (name, username) VALUES ('Favorite songs', 'huuvu');

INSERT INTO songs_playlists(playlist_id, song_id) VALUES (1, 1), (1, 2), (1, 3);