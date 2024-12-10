package com.alquilaya.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alquilaya.dao.IProductCharacteristicDao;
import com.alquilaya.entity.ProductCharacteristic;

@Service
public class ProductCharacteristicService  implements IProductCharacteristicService{

	@Autowired
	IProductCharacteristicDao dao;
	@Override
	public ProductCharacteristic saveProductCharacteristic(ProductCharacteristic characteristic) {
		
		return dao.saveProductCharacteristic(characteristic);
	}

	@Override
	public List<ProductCharacteristic> findAllProductCharacteristics() {
		
		return dao.findAllProductCharacteristics();
	}

	@Override
	public Optional<ProductCharacteristic> findProductCharacteristicById(Integer id) {
		
		return dao.findProductCharacteristicById(id);
	}

	@Override
	public List<ProductCharacteristic> findCharacteristicsByProductId(Integer productId) {
		
		return null;
	}

	@Override
	public ProductCharacteristic updateProductCharacteristic(ProductCharacteristic characteristic, Integer id) {
		
		return dao.updateProductCharacteristic(characteristic, id);
	}

}
