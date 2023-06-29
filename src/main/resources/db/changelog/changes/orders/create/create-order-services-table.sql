CREATE TABLE IF NOT EXISTS order_services (
                                              order_id BIGINT,
                                              service_id BIGINT,
                                              FOREIGN KEY (order_id) REFERENCES orders (id),
    FOREIGN KEY (service_id) REFERENCES services (id)
    );
