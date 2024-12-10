package com.alquilaya.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.alquilaya.entity.Order;

public interface IOrderDao {
	Order saveOrder(Order order);
    List<Order> findAllOrders();
    Optional<Order> findOrderById(Integer id);
    List<Order> findOrdersByUserId(Integer userId);
    Order updateOrder(Order order, Integer id);
    Integer findOrderByPending(Integer userId);
}
