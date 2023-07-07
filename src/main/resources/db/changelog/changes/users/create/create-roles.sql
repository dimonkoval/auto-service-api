CREATE TABLE IF NOT EXISTS roles (
                                     id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                                     role_name VARCHAR(50)
    );