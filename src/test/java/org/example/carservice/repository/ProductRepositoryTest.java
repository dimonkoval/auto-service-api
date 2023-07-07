package org.example.carservice.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.List;
import org.example.carservice.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {
    @Container
    static PostgreSQLContainer<?> database = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("springboot")
            .withUsername("springboot")
            .withPassword("springboot");

    @DynamicPropertySource
    static void setDataSourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.username", database::getUsername);
        registry.add("spring.datasource.password", database::getPassword);
        registry.add("spring.datasource.url", database::getJdbcUrl);
    }

    @Autowired
    ProductRepository productRepository;

    @Test
    void findAllByPriceBetween_Ok() {
        List<Product> actual =
                productRepository.findAllByPriceBetween(BigDecimal.valueOf(100),
                        BigDecimal.valueOf(2000),
                        Pageable.ofSize(10));
        assertEquals(3L, actual.size());
        assertEquals(Double.valueOf(100), actual.get(1).getPrice().doubleValue());
        assertNotNull(actual.get(0).getId());
    }
}