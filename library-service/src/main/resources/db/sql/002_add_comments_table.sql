SET search_path = public;

DROP TABLE IF EXISTS comment;
DROP SEQUENCE IF EXISTS comment_sequence;

CREATE TABLE comment (
    id BIGSERIAL NOT NULL,
    author_id BIGINT NOT NULL,
    album_id BIGINT NOT NULL,
    content TEXT,
    score INTEGER,
    created_at TIMESTAMP
);
ALTER TABLE comment ADD CONSTRAINT comment_pkey PRIMARY KEY (id);
ALTER TABLE comment ADD CONSTRAINT album_id FOREIGN KEY (album_id) REFERENCES album(id) ON DELETE CASCADE;
CREATE SEQUENCE comment_sequence START WITH 1;