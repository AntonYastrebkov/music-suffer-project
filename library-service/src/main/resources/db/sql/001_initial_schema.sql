SET search_path = public;

DROP TABLE IF EXISTS music;
DROP TABLE IF EXISTS album CASCADE;
DROP TABLE IF EXISTS artist CASCADE;
DROP TABLE IF EXISTS comment CASCADE;
DROP SEQUENCE IF EXISTS artist_sequence;
DROP SEQUENCE IF EXISTS music_sequence;
DROP SEQUENCE IF EXISTS album_sequence;
DROP SEQUENCE IF EXISTS comment_sequence;

CREATE TABLE artist (
    id          BIGINT NOT NULL,
    name        TEXT NOT NULL,
    biography   TEXT,
    is_group    BOOLEAN DEFAULT FALSE,
    country     TEXT NOT NULL,
    image_path  TEXT
);
ALTER TABLE artist ADD CONSTRAINT artist_pkey PRIMARY KEY (id);
CREATE SEQUENCE artist_sequence START WITH 1;
INSERT INTO artist (id, name, country)
    VALUES (0, 'default', 'Narnia');

CREATE TABLE album (
    id              BIGINT NOT NULL,
    name            TEXT NOT NULL,
    cover_path      TEXT,
    year            INTEGER NOT NULL,
    votes           INTEGER,
    average_score   FLOAT,
    genre           TEXT NOT NULL,
    artist_id       BIGINT NOT NULL
);
ALTER TABLE album ADD CONSTRAINT album_pkey PRIMARY KEY (id);
ALTER TABLE album ADD CONSTRAINT fk_artist_id FOREIGN KEY (artist_id) REFERENCES artist(id) ON DELETE CASCADE;
CREATE SEQUENCE album_sequence START WITH 1;
INSERT INTO album (id, name, year, genre, artist_id)
    VALUES (0, 'default', 0, 'OTHER', 0);

CREATE TABLE music (
    id BIGINT NOT NULL,
    name TEXT NOT NULL,
    position INTEGER,
    year INTEGER,
    genre TEXT,
    album_id BIGINT NOT NULL,
    artist_id       BIGINT NOT NULL,
    listening INTEGER
);
ALTER TABLE music ADD CONSTRAINT music_pkey PRIMARY KEY (id);
ALTER TABLE music ADD CONSTRAINT fk_artist_id FOREIGN KEY (artist_id) REFERENCES artist(id) ON DELETE CASCADE;
ALTER TABLE music ADD CONSTRAINT fk_album_id FOREIGN KEY (album_id) REFERENCES album(id) ON DELETE CASCADE;
CREATE SEQUENCE music_sequence START WITH 1;

CREATE TABLE comment (
    id          BIGINT NOT NULL,
    author_id   BIGINT NOT NULL,
    album_id    BIGINT NOT NULL,
    text        TEXT NOT NULL,
    score       INTEGER,
    time        TIMESTAMP NOT NULL
);
ALTER TABLE comment ADD CONSTRAINT comment_pkey PRIMARY KEY (id);
ALTER TABLE comment ADD CONSTRAINT fk_com_album_id FOREIGN KEY (album_id) REFERENCES album(id) ON DELETE CASCADE;
CREATE SEQUENCE comment_sequence START WITH 1;

CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1;