package com.alquilaya.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alquilaya.dao.IOrderDao;
import com.alquilaya.dao.IOrderDetailDao;
import com.alquilaya.entity.Order;
import com.alquilaya.entity.OrderDetail;

@Service
public class OrderDetailService implements IOrderDetailService {

	@Autowired
	IOrderDetailDao dao;
	
	@Autowired
	IOrderDao orderDao;
	
	@Override
	public OrderDetail saveOrderDetail(OrderDetail orderDetail, Integer userID) {
		Integer findedOrderId = orderDao.findOrderByPending(userID);
		if (findedOrderId != 0) {
			orderDetail.setOrderId(findedOrderId);
		} else {
			Order newOrder = new Order();
			newOrder.setOrderDate(new java.sql.Timestamp(System.currentTimeMillis()));
			newOrder.setUserId(userID);
			newOrder.setStatus("pending");
			Order order  = orderDao.saveOrder(newOrder);
			int id = order.getOrderId();
			orderDetail.setOrderId(id);
		}
		return dao.saveOrderDetail(orderDetail);
	}

	@Override
	public List<OrderDetail> findAllOrderDetails() {
		return dao.findAllOrderDetails();
	}

	@Override
	public Optional<OrderDetail> findOrderDetailById(Integer id) {
		return dao.findOrderDetailById(id);
	}

	@Override
	public List<OrderDetail> findOrderDetailsByOrderId(Integer orderId) {
		return dao.findOrderDetailsByOrderId(orderId);
	}

	@Override
	public OrderDetail updateOrderDetail(OrderDetail orderDetail, Integer id) {
		return dao.updateOrderDetail(orderDetail, id);
	}

	@Override
	public List<OrderDetail> cart(Integer userId) {
		return dao.cart(userId);
	}

}
