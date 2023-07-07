package org.example.carservice.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.List;
import org.example.carservice.model.Order;
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
class MasterRepositoryTest {
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
    private MasterRepository masterRepository;

    @Test
    void findAllOrdersByMasterId_ok() {
        List<Order> actual = masterRepository.findAllOrdersByMasterId(1L);
        assertNotNull(actual);
        assertEquals(4, actual.size());
        assertEquals(Double.valueOf(350.5), actual.get(3).getCostTotal().doubleValue());
        assertEquals(2L, actual.get(1).getCar().getOwner().getId());
    }

    @Test
    void getSalaryOfMasterByOrder_Ok() {
        BigDecimal actual = masterRepository.getSalaryOfMasterByOrder(2L, 4L);
        assertNotNull(actual);
        assertEquals(BigDecimal.valueOf(1200.75), actual);
    }
}
