package org.example.carservice.controller;

import static org.hamcrest.Matchers.equalTo;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import java.math.BigDecimal;
import org.example.carservice.service.MasterService;
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
class MasterControllerTest {
    private static final String ADMIN_EMAIL = "admin@gmail.com";
    private static final String ADMIN_PASSWORD = "admin";
    @MockBean
    private MasterService masterService;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    @WithMockUser(username = ADMIN_EMAIL, password = ADMIN_PASSWORD)
    void getSalaryOfMasterByOrder_Ok() {

        Mockito.when(masterService.getSalaryOfMasterByOrder(1L, 1L))
                .thenReturn(BigDecimal.valueOf(1000));

        RestAssuredMockMvc
                .given()
                .queryParam("masterId", 1L)
                .queryParam("orderId", 1L)
                .when()
                .get("/masters/salary")
                .then()
                .statusCode(200)
                .body(equalTo("1000"));
    }
}