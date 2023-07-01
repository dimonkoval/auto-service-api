CREATE TABLE IF NOT EXISTS orders (
                                      id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                                      car_id BIGINT,
                                      problem_description VARCHAR(255),
    date_acceptance TIMESTAMP,
    date_completion TIMESTAMP,
    status_order VARCHAR(50),
    cost_total DECIMAL(10, 2),
    FOREIGN KEY (car_id) REFERENCES cars (id)
    );
