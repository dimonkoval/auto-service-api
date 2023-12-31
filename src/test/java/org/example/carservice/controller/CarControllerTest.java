package org.example.carservice.controller;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import java.util.ArrayList;
import java.util.List;
import org.example.carservice.model.Car;
import org.example.carservice.model.Owner;
import org.example.carservice.service.CarService;
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
class CarControllerTest {
    private static final String ADMIN_EMAIL = "admin@gmail.com";
    private static final String ADMIN_PASSWORD = "admin";
    @MockBean
    private CarService carService;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    @WithMockUser(username = ADMIN_EMAIL, password = ADMIN_PASSWORD)
    void findAll_Ok() {
        Owner owner = new Owner();
        owner.setId(1L);
        owner.setCars(new ArrayList<>());
        owner.setOrders(new ArrayList<>());

        Car car1 = new Car();
        car1.setId(10L);
        car1.setName("Old Name");
        car1.setModel("Old Model");
        car1.setOwner(owner);
        car1.setNumber("Old Number");
        car1.setYearOfIssue(2020);

        Car car2 = new Car();
        car2.setId(20L);
        car2.setName("New Name");
        car2.setModel("New Model");
        car2.setOwner(owner);
        car2.setNumber("New Number");
        car2.setYearOfIssue(2022);
        List<Car> mockCars = List.of(car1, car2);

        Mockito.when(carService.findAll()).thenReturn(mockCars);

        RestAssuredMockMvc.when()
                .get("/cars")
                .then()
                .statusCode(200)
                .body("size()", Matchers.equalTo(2))
                .body("[1].id", Matchers.equalTo(20))
                .body("size()", Matchers.equalTo(2))
                .body("[1].id", Matchers.equalTo(20))
                .body("[1].model", Matchers.equalTo("New Model"))
                .body("[0].model", Matchers.equalTo("Old Model"))
                .body("[0].yearOfIssue", Matchers.equalTo(2020))
                .body("[1].yearOfIssue", Matchers.equalTo(2022));
    }
}