CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                                     email VARCHAR(255) UNIQUE,
    password VARCHAR(255)
    );