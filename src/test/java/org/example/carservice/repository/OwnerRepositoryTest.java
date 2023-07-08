package org.example.carservice.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;
import org.example.carservice.exception.DataProcessingException;
import org.example.carservice.model.Order;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OwnerRepositoryTest {
    @Container
    static PostgreSQLContainer<?> database;

    static {
        try {
            database = new PostgreSQLContainer<>("postgres:latest")
                    .withDatabaseName("carService")
                    .withUsername("carService")
                    .withPassword("carService");
            database.start();
        } catch (DataProcessingException e) {
            throw new DataProcessingException("Failed to start container", e);
        }
    }

    @DynamicPropertySource
    static void setDataSourceProperties(DynamicPropertyRegistry registry) {
        if (database != null) {
            try {
                registry.add("spring.datasource.username", database::getUsername);
                registry.add("spring.datasource.password", database::getPassword);
                registry.add("spring.datasource.url", database::getJdbcUrl);
            } catch (DataProcessingException e) {
                throw new DataProcessingException("Failed to set registration properties", e);
            }
        }
    }

    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    void findAllOrdersByOwnerId_Ok() {
        List<Order> actual = ownerRepository.findAllOrdersByOwnerId(5L);
        assertNotNull(actual);
        assertEquals(2, actual.size());
        assertEquals(2L, actual.get(1).getCar().getOwner().getId());
        assertNotEquals(1L, actual.get(0).getCar().getOwner().getId());
    }

    @AfterAll
    static void tearDown() {
        if (database != null) {
            try {
                database.stop();
            } catch (DataProcessingException e) {
                throw new DataProcessingException("container did not close", e);
            }
        }
    }
}