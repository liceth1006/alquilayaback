package com.alquilaya.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.alquilaya.dao.IProductCharacteristicValueDao;
import com.alquilaya.entity.ProductCharacteristicValue;

@Service
public class ProductCharacteristicValueService  implements IProductCharacteristicValueService{

	
	@Autowired
	IProductCharacteristicValueDao dao;
	
	@Override
	public ProductCharacteristicValue saveProductCharacteristic(ProductCharacteristicValue characteristic) {
		
		return dao.saveProductCharacteristic(characteristic);
	}

	@Override
	public List<ProductCharacteristicValue> findAllProductCharacteristics() {
		
		return dao.findAllProductCharacteristics();
	}

	@Override
	public Optional<ProductCharacteristicValue> findProductCharacteristicById(Integer id) {
		// TODO Auto-generated method stub
		return dao.findProductCharacteristicById(id);
	}

	@Override
	public List<ProductCharacteristicValue> findCharacteristicsByProductId(Integer productId) {
		
		return dao.findCharacteristicsByProductId(productId);
	}

	@Override
	public ProductCharacteristicValue updateProductCharacteristic(ProductCharacteristicValue characteristic,
			Integer id) {
		
		return dao.updateProductCharacteristic(characteristic, id);
	}

}
