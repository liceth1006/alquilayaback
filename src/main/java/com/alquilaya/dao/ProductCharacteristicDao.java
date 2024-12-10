package com.alquilaya.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alquilaya.entity.ProductCharacteristic;
import com.alquilaya.jpa.IProductCharacteristicJpa;

import jakarta.persistence.EntityNotFoundException;

@Repository
public class ProductCharacteristicDao  implements IProductCharacteristicDao{

	
	@Autowired
	IProductCharacteristicJpa jpa;
	@Override
	public ProductCharacteristic saveProductCharacteristic(ProductCharacteristic characteristic) {
		return jpa.save(characteristic);
	}

	@Override
	public List<ProductCharacteristic> findAllProductCharacteristics() {
		return jpa.findAll();
	}

	@Override
	public Optional<ProductCharacteristic> findProductCharacteristicById(Integer id) {
		return jpa.findById(id);
	}

	@Override
	public ProductCharacteristic updateProductCharacteristic(ProductCharacteristic characteristic, Integer id) {
		if (jpa.existsById(id)) {
			characteristic.setCharacteristicId(id);  
            return jpa.save(characteristic);  
        }
        throw new EntityNotFoundException("Address with ID " + id + " not found");  
    }

}
