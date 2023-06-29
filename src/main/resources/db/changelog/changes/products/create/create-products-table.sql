CREATE TABLE IF NOT EXISTS products (
                                        id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                                        title VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL
    );
