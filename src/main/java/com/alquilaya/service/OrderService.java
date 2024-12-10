package com.alquilaya.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alquilaya.dao.IOrderDao;
import com.alquilaya.entity.Order;

@Service
public class OrderService implements IOrderService {

	@Autowired
	IOrderDao dao;
	
	@Override
	public List<Order> findAllOrders() {
		return dao.findAllOrders();
	}

	@Override
	public Optional<Order> findOrderById(Integer id) {
		return dao.findOrderById(id);
	}

	@Override
	public List<Order> findOrdersByUserId(Integer userId) {
		return dao.findOrdersByUserId(userId);
	}

	@Override
	public Order updateOrder(Order order, Integer id) {
		return dao.updateOrder(order, id);
	}

	@Override
	public Order completeOrder(Order order, Integer id) {
		order.setOrderDate(new java.sql.Timestamp(System.currentTimeMillis()));
		order.setStatus("completed");
		return dao.updateOrder(order, id);
	}

}
