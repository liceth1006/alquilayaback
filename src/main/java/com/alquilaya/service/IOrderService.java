package com.alquilaya.service;

import java.util.List;
import java.util.Optional;

import com.alquilaya.entity.Order;

public interface IOrderService {
    List<Order> findAllOrders();
    Optional<Order> findOrderById(Integer id);
    List<Order> findOrdersByUserId(Integer userId);
    Order updateOrder(Order order, Integer id);
    Order completeOrder(Order order, Integer id);
}
