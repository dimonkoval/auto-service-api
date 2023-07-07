package org.example.carservice.service;

import java.math.BigDecimal;
import java.util.List;
import org.example.carservice.model.Master;
import org.example.carservice.model.Order;

public interface MasterService {
    Master create(Master master);

    Master update(Master master);

    List<Order> findAllOrdersByMasterId(Long masterId);

    BigDecimal getSalaryOfMasterByOrder(Long masterId, Long orderId);

    Master getById(Long id);

    List<Master> findAll();
}
