package com.alquilaya.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alquilaya.entity.Order;
import com.alquilaya.jpa.IOrderJpa;

import jakarta.persistence.EntityNotFoundException;

@Repository
public class OrderDao implements IOrderDao {

	
	@Autowired
	IOrderJpa jpa;
	
	@Override
	public Order saveOrder(Order order) {
		return jpa.save(order);
	}

	@Override
	public List<Order> findAllOrders() {
		return jpa.findAll();
	}

	@Override
	public Optional<Order> findOrderById(Integer id) {
		return jpa.findById(id);
	}

	@Override
	public List<Order> findOrdersByUserId(Integer userId) {
		return jpa.findByUserId(userId);
	}

	@Override
	public Order updateOrder(Order order, Integer id) {
		if (jpa.existsById(id)) {
			order.setOrderId(id);  
            return jpa.save(order);  
        }
        throw new EntityNotFoundException("Address with ID " + id + " not found");  
	}

	@Override
	public Integer findOrderByPending(Integer userId) {
		Optional<Order> orderOptional = jpa.findOrderByPending(userId); 
		return orderOptional.map(Order::getOrderId).orElse(0);
	}
}
