CREATE TABLE IF NOT EXISTS services (
                                        id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                                        status_service VARCHAR(255),
    cost_service DOUBLE PRECISION
    );
