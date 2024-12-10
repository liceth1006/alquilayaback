package com.alquilaya.service;

import java.util.List;
import java.util.Optional;

import com.alquilaya.entity.OrderDetail;

public interface IOrderDetailService {
	OrderDetail saveOrderDetail(OrderDetail orderDetail, Integer userID);
    List<OrderDetail> findAllOrderDetails();
    Optional<OrderDetail> findOrderDetailById(Integer id);
    List<OrderDetail> findOrderDetailsByOrderId(Integer orderId);
    OrderDetail updateOrderDetail(OrderDetail orderDetail, Integer id);
    List<OrderDetail> cart (Integer userId);
}
