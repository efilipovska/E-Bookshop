CREATE TABLE book_activity_logs (
        id BIGSERIAL PRIMARY KEY,
        book_name VARCHAR(255) NOT NULL,
        event_time TIMESTAMP NOT NULL,
        event_type VARCHAR(100) NOT NULL
);