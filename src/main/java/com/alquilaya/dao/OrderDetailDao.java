package com.alquilaya.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alquilaya.entity.OrderDetail;
import com.alquilaya.jpa.IOrderDetailJpa;

import jakarta.persistence.EntityNotFoundException;


@Repository
public class OrderDetailDao implements IOrderDetailDao {

	
	@Autowired
	IOrderDetailJpa jpa;
	
	@Override
	public OrderDetail saveOrderDetail(OrderDetail orderDetail) {
		return jpa.save(orderDetail);
				
	}

	@Override
	public List<OrderDetail> findAllOrderDetails() {
		return jpa.findAll();
	}

	@Override
	public Optional<OrderDetail> findOrderDetailById(Integer id) {
		// TODO Auto-generated method stub
		return jpa.findById(id);
	}
	
	@Override
	public List<OrderDetail> findOrderDetailsByOrderId(Integer orderId) {
		 return jpa.findByOrderId(orderId);
	}
	
	@Override
	public OrderDetail updateOrderDetail(OrderDetail orderDetail, Integer id) {
		if (jpa.existsById(id)) {
			orderDetail.setOrderDetailId(id);  
            return jpa.save(orderDetail);  
        }
        throw new EntityNotFoundException("Address with ID " + id + " not found");  
    }

	@Override
	public List<OrderDetail> cart(Integer userId) {
		return jpa.cart(userId);
	}

}
