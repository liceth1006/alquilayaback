package com.alquilaya.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alquilaya.entity.Order;

public interface IOrderJpa extends JpaRepository<Order, Integer>  {
	
	@Query("SELECT o FROM Order o WHERE o.userId = ?1")
	List<Order> findByUserId(Integer userId);
	
	@Query("SELECT o FROM Order o WHERE o.userId = ?1 and o.status = 'pending'")
	Optional<Order> findOrderByPending(Integer userId);
}
