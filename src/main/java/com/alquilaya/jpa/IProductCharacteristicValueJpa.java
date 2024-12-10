package com.alquilaya.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.alquilaya.entity.ProductCharacteristicValue;

public interface IProductCharacteristicValueJpa extends JpaRepository<ProductCharacteristicValue, Integer>{
	List<ProductCharacteristicValue> findByProduct_ProductId(Integer productId);

}
