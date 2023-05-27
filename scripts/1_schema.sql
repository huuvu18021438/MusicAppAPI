CREATE TYPE music_genre AS ENUM ('Pop','Rock', 'EDM', 'Country', 'Dance');

CREATE CAST (varchar AS music_genre) WITH INOUT AS IMPLICIT;

CREATE TABLE songs (
       id SERIAL PRIMARY KEY,
       name VARCHAR (255) NOT NULL,
       genre music_genre NOT NULL,
       author VARCHAR (255) NOT NULL,
       release_year INTEGER CHECK (release_year > 1990),
       singer VARCHAR (255) NOT NULL,
       vote INTEGER CHECK (vote >= 0) DEFAULT 0,
       lyric TEXT,
       image TEXT,
       album_id INTEGER NOT NULL,
       file_id INTEGER NOT NULL,
       created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
       last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE playlists (
     id SERIAL PRIMARY KEY,
     name VARCHAR (255) NOT NULL,
     created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE albums (
       id SERIAL PRIMARY KEY,
       name VARCHAR(255) NOT NULL,
       description VARCHAR,
       created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
       last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE files (
       id SERIAL PRIMARY KEY,
       name VARCHAR (255) NOT NULL,
       type VARCHAR (255) NOT NULL,
       raw_data BYTEA,
       created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
       last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE songs
ADD CONSTRAINT fk_songs_files
FOREIGN KEY (file_id)
REFERENCES files (id);

ALTER TABLE songs
ADD CONSTRAINT fk_songs_albums
FOREIGN KEY (album_id)
REFERENCES albums (id);

CREATE TABLE songs_playlists (
      id SERIAL PRIMARY KEY,
      song_id INTEGER REFERENCES songs(id),
      playlist_id INTEGER REFERENCES playlists(id)
);