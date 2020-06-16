SET search_path = public;

DROP TABLE IF EXISTS music;
DROP TABLE IF EXISTS album CASCADE;
DROP TABLE IF EXISTS artist CASCADE;
DROP SEQUENCE IF EXISTS artist_sequence;
DROP SEQUENCE IF EXISTS music_sequence;
DROP SEQUENCE IF EXISTS album_sequence;

CREATE TABLE artist (
    id          BIGINT NOT NULL,
    name        TEXT NOT NULL,
    biography   TEXT,
    is_group    BOOLEAN DEFAULT FALSE,
    country     TEXT NOT NULL,
    image_path  TEXT,
    is_deleted  BOOLEAN NOT NULL DEFAULT FALSE
);
ALTER TABLE artist ADD CONSTRAINT artist_pkey PRIMARY KEY (id);
CREATE SEQUENCE artist_sequence START WITH 1;
INSERT INTO artist (id, name, country, is_deleted)
    VALUES (0, 'default', 'Narnia', true);

CREATE TABLE album (
    id              BIGINT NOT NULL,
    name            TEXT NOT NULL,
    cover_path      TEXT,
    year            INTEGER NOT NULL,
    votes           INTEGER,
    average_score   FLOAT,
    genre           TEXT NOT NULL,
    artist_id       BIGINT NOT NULL,
    is_deleted      BOOLEAN NOT NULL DEFAULT FALSE
);
ALTER TABLE album ADD CONSTRAINT album_pkey PRIMARY KEY (id);
ALTER TABLE album ADD CONSTRAINT fk_artist_id FOREIGN KEY (artist_id) REFERENCES artist(id) ON DELETE CASCADE;
CREATE SEQUENCE album_sequence START WITH 1;
INSERT INTO album (id, name, year, genre, artist_id, is_deleted)
    VALUES (0, 'default', 0, 'OTHER', 0, true);

CREATE TABLE music (
    id          BIGINT NOT NULL,
    name        TEXT NOT NULL,
    position    INTEGER,
    year        INTEGER,
    genre       TEXT,
    album_id    BIGINT NOT NULL,
    artist_id   BIGINT NOT NULL,
    listening   INTEGER
);
ALTER TABLE music ADD CONSTRAINT music_pkey PRIMARY KEY (id);
ALTER TABLE music ADD CONSTRAINT fk_artist_id FOREIGN KEY (artist_id) REFERENCES artist(id) ON DELETE CASCADE;
ALTER TABLE music ADD CONSTRAINT fk_album_id FOREIGN KEY (album_id) REFERENCES album(id) ON DELETE CASCADE;
CREATE SEQUENCE music_sequence START WITH 1;

CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1;