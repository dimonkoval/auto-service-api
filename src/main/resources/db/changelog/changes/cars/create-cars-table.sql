CREATE TABLE IF NOT EXISTS cars (
                                    id BIGSERIAL PRIMARY KEY,
                                    name VARCHAR(255),
    model VARCHAR(255),
    year_of_issue INT,
    number VARCHAR(255),
    owner_id BIGINT,
    FOREIGN KEY (owner_id) REFERENCES owners (id)
    );
