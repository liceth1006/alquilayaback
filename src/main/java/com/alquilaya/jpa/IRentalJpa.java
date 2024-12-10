package com.alquilaya.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alquilaya.entity.Rental;

public interface IRentalJpa  extends JpaRepository<Rental, Integer>{
	
	@Query("SELECT r FROM Rental r WHERE r.supplierId = ?1")
	List<Rental> findBySupplierId(Integer supplierId);
	
	@Query("SELECT r FROM Rental r "
			+ "INNER JOIN OrderDetail o on o.orderDetailId = r.orderDetail.orderDetailId "
			+ "WHERE o.product.productId = ?1")
    List<Rental> findByProductId(Integer productId);
	
}
