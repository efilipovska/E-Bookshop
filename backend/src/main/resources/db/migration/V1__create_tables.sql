CREATE TABLE countries(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    continent VARCHAR(100) NOT NULL
);

CREATE TABLE authors(
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    name VARCHAR(100) NOT NULL,
    surname VARCHAR(100) NOT NULL,
    country_id BIGINT NOT NULL REFERENCES countries(id) ON DELETE CASCADE
);

CREATE TABLE books(
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    name VARCHAR(200) NOT NULL,
    category VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL,
    available_copies INT NOT NULL,
    author_id BIGINT NOT NULL REFERENCES authors(id) ON DELETE CASCADE,
    deleted BOOLEAN NOT NULL DEFAULT false
);