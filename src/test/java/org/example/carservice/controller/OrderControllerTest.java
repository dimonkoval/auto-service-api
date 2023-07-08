package org.example.carservice.controller;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.example.carservice.model.Car;
import org.example.carservice.model.Order;
import org.example.carservice.model.Order.StatusOrder;
import org.example.carservice.model.Owner;
import org.example.carservice.service.OrderService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {
    private static final String ADMIN_EMAIL = "admin@gmail.com";
    private static final String ADMIN_PASSWORD = "admin";
    @MockBean
    private OrderService orderService;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    @WithMockUser(username = ADMIN_EMAIL, password = ADMIN_PASSWORD)
    void getById_Ok() {
        Owner owner = new Owner();
        owner.setId(1L);
        owner.setCars(new ArrayList<>());
        owner.setOrders(new ArrayList<>());

        Car car = new Car();
        car.setId(10L);
        car.setName("Old Name");
        car.setModel("Old Model");
        car.setOwner(owner);
        car.setNumber("Old Number");
        car.setYearOfIssue(2020);

        Order order = new Order();
        order.setId(1L);
        order.setStatusOrder(StatusOrder.IN_PROGRESS);
        order.setCar(car);
        order.setServices(new ArrayList<>());
        order.setCostTotal(BigDecimal.valueOf(999));
        order.setProducts(new ArrayList<>());
        order.setDateOfAcceptance(LocalDateTime.now());
        order.setDateCompletion(null);
        order.setProblemDescription("dont work");

        Mockito.when(orderService.getById(1L)).thenReturn(order);
        RestAssuredMockMvc
                .given()
                .queryParam("id", 1L)
                .when()
                .get("/orders/1")
                .then()
                .statusCode(200)
                .body("statusOrder", Matchers.equalTo(StatusOrder.IN_PROGRESS.name()))
                .body("id", Matchers.equalTo(1))
                .body("serviceIds", Matchers.empty())
                .body("productIds", Matchers.empty())
                .body("problemDescription", Matchers.equalTo("dont work"));
    }
}