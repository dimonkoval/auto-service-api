package org.example.carservice.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Optional;
import org.example.carservice.exception.DataProcessingException;
import org.example.carservice.model.Role;
import org.example.carservice.model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {
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

    private static final String EMAIL_ADMIN = "admin@gmail.com";
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Test
    @Sql({"classpath:scripts/test-insert-users.sql",
            "classpath:scripts/test-insert-users-roles.sql"})
    void findByEmail_Ok() {
        Optional<User> optionalUser = userRepository.findByEmail(EMAIL_ADMIN);
        if (optionalUser.isPresent()) {
            User actual = optionalUser.get();
            assertNotNull(actual);
            ArrayList<Role> roles = new ArrayList<>(actual.getRoles());
            assertEquals(2L, roles.size());
            assertTrue(roles.contains(roleRepository.getById(1L)));
            assertTrue(roles.contains(roleRepository.getById(2L)));
        }
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
