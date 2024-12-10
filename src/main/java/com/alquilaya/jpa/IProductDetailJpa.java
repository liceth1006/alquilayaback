package com.alquilaya.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alquilaya.entity.ProductDetails;


public interface IProductDetailJpa extends JpaRepository<ProductDetails, Integer> {
	List<ProductDetails> findByProduct_ProductId(Integer productId);	

}
