package com.alquilaya.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alquilaya.entity.OrderDetail;

public interface IOrderDetailJpa extends JpaRepository<OrderDetail, Integer> {
	
	@Query("SELECT o FROM OrderDetail o WHERE o.orderId = ?1")
	List<OrderDetail> findByOrderId(Integer orderId);
	
	@Query("SELECT od FROM OrderDetail od "
			+ "INNER JOIN Order o ON o.orderId = od.orderId "
			+ "WHERE o.userId = ?1 and o.status = 'pending' ")
	List<OrderDetail> cart(Integer userId);
}
