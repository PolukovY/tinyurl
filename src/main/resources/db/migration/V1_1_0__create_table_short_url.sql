CREATE SEQUENCE IF NOT EXISTS short_url_id_sequence;

CREATE TABLE IF NOT EXISTS short_url (
    id BIGINT NOT NULL,
    long_url VARCHAR NOT NULL,
    created_at timestamp NOT NULL DEFAULT NOW(),
    expired_at timestamp NOT NULL,
    PRIMARY KEY (id)
);