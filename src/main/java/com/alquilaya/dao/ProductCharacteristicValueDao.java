package com.alquilaya.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alquilaya.entity.ProductCharacteristicValue;
import com.alquilaya.jpa.IProductCharacteristicValueJpa;

import jakarta.persistence.EntityNotFoundException;

@Repository
public class ProductCharacteristicValueDao implements IProductCharacteristicValueDao{

	@Autowired
	IProductCharacteristicValueJpa jpa;
	
	@Override
	public ProductCharacteristicValue saveProductCharacteristic(ProductCharacteristicValue characteristic) {
		
		return jpa.save(characteristic);
	}

	@Override
	public List<ProductCharacteristicValue> findAllProductCharacteristics() {
		
		return jpa.findAll();
	}

	@Override
	public Optional<ProductCharacteristicValue> findProductCharacteristicById(Integer id) {
		
		return jpa.findById(id);
	}

	@Override
	public List<ProductCharacteristicValue> findCharacteristicsByProductId(Integer productId) {
				return jpa.findByProduct_ProductId(productId);
	}

	@Override
	public ProductCharacteristicValue updateProductCharacteristic(ProductCharacteristicValue characteristic,
			Integer id) {
		if (jpa.existsById(id)) {
			characteristic.setValueId(id);  
            return jpa.save(characteristic);  
        }
        throw new EntityNotFoundException("Address with ID " + id + " not found");  
    }
	}


