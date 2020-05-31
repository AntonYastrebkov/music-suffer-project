SET search_path = public;

DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS tokens;

CREATE TABLE users (
    id          BIGINT NOT NULL,
    email       TEXT NOT NULL,
    password    TEXT NOT NULL,
    first_name  TEXT NOT NULL,
    last_name   TEXT,
    roles       JSONB,
    enabled     BOOLEAN NOT NULL
);
ALTER TABLE users ADD CONSTRAINT users_pkey PRIMARY KEY (id);

CREATE TABLE tokens (
    jti             UUID NOT NULL,
    access_token    TEXT NOT NULL,
    refresh_token   TEXT,
    token_type      TEXT,
    expiration      TIMESTAMP,
    user_id         BIGINT NOT NULL
);
ALTER TABLE tokens ADD CONSTRAINT tokens_pkey PRIMARY KEY (jti);
ALTER TABLE tokens ADD CONSTRAINT fk_users_id FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE;

CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1;