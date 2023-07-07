package org.example.carservice.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.example.carservice.model.Car;
import org.example.carservice.model.Order;
import org.example.carservice.model.Order.StatusOrder;
import org.example.carservice.model.Product;
import org.example.carservice.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private OrderRepository orderRepository;

    @Test
    void addProduct_Ok() {

        Order order = new Order();
        order.setProducts(new ArrayList<>());
        order.setServices(new ArrayList<>());
        order.setStatusOrder(StatusOrder.IN_PROGRESS);
        order.setCar(new Car());
        order.setProblemDescription("dont work car");
        order.setDateCompletion(null);
        order.setDateOfAcceptance(LocalDateTime.now());
        order.setCostTotal(BigDecimal.valueOf(2500));
        order.setId(1L);

        Product product = new Product();
        product.setId(1L);
        product.setPrice(BigDecimal.valueOf(1300));
        product.setTitle("Product test");

        when(orderRepository.getById(1L)).thenReturn(order);
        Order updatedOrder = orderService.addProduct(order, product);
        assertNotNull(updatedOrder);
        Product mockProduct = updatedOrder.getProducts().get(0);
        assertEquals(mockProduct, product);
        assertEquals(updatedOrder.getStatusOrder(), StatusOrder.IN_PROGRESS);
        assertEquals(updatedOrder.getServices().size(), 0);
        assertEquals(updatedOrder.getCostTotal(), BigDecimal.valueOf(2500));
        assertEquals(mockProduct.getPrice(), BigDecimal.valueOf(1300));
    }
}