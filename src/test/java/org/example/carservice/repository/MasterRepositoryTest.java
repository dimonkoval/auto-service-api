package org.example.carservice.repository;

import java.util.List;
import org.example.carservice.model.Order;
import org.junit.jupiter.api.Assertions;
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

//@SpringBootTest(properties = {
//        "spring.datasource.url=jdbc:tc:postgresql://localhost:5432/postgres"
//})
//@BootstrapWith(SpringBootTestContextBootstrapper.class)
@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MasterRepositoryTest {
    @Container
    static PostgreSQLContainer<?> database = new PostgreSQLContainer<>("postgres:15.3")
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
    @Sql("scripts/init_master_orders.sql")
    void findAllOrdersByMasterId_ok() {
        List<Order> actual = masterRepository.findAllOrdersByMasterId(1L);
        Assertions.assertEquals(5, actual.size());

    }
}