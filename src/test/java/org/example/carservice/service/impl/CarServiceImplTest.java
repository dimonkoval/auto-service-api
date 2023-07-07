package org.example.carservice.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import org.example.carservice.model.Car;
import org.example.carservice.model.Owner;
import org.example.carservice.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {
    @InjectMocks
    private CarServiceImpl carService;
    @Mock
    private CarRepository carRepository;

    @Test
    void update_Ok() {
        Owner owner = new Owner();
        owner.setId(1L);
        owner.setCars(new ArrayList<>());
        owner.setOrders(new ArrayList<>());

        Car existingCar = new Car();
        existingCar.setId(1L);
        existingCar.setName("Old Name");
        existingCar.setModel("Old Model");
        existingCar.setOwner(owner);
        existingCar.setNumber("Old Number");
        existingCar.setYearOfIssue(2020);

        Car updatedCar = new Car();
        updatedCar.setId(1L);
        updatedCar.setName("New Name");
        updatedCar.setModel("New Model");
        updatedCar.setOwner(owner);
        updatedCar.setNumber("New Number");
        updatedCar.setYearOfIssue(2022);

        when(carRepository.getById(existingCar.getId())).thenReturn(existingCar);
        when(carRepository.save(any(Car.class))).thenReturn(updatedCar);

        Car result = carService.update(updatedCar);

        assertEquals(updatedCar.getName(), result.getName());
        assertEquals(updatedCar.getModel(), result.getModel());
        assertEquals(updatedCar.getOwner(), result.getOwner());
        assertEquals(updatedCar.getNumber(), result.getNumber());
        assertEquals(updatedCar.getYearOfIssue(), result.getYearOfIssue());
    }
}