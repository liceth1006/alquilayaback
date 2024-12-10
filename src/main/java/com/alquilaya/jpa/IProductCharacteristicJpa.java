package com.alquilaya.jpa;



import org.springframework.data.jpa.repository.JpaRepository;


import com.alquilaya.entity.ProductCharacteristic;

public interface IProductCharacteristicJpa extends JpaRepository<ProductCharacteristic, Integer>{
	
}
