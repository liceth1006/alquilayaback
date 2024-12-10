package com.alquilaya.dao;

import java.util.List;
import java.util.Optional;

import com.alquilaya.entity.OrderDetail;

public interface IOrderDetailDao {
	OrderDetail saveOrderDetail(OrderDetail orderDetail);
    List<OrderDetail> findAllOrderDetails();
    Optional<OrderDetail> findOrderDetailById(Integer id);
    List<OrderDetail> findOrderDetailsByOrderId(Integer orderId);
    OrderDetail updateOrderDetail(OrderDetail orderDetail, Integer id);
    List<OrderDetail> cart (Integer userId);
}
