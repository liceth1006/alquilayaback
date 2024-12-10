package com.alquilaya.dao;

import java.util.List;
import java.util.Optional;

import com.alquilaya.entity.ProductCharacteristic;

public interface IProductCharacteristicDao {
	ProductCharacteristic saveProductCharacteristic(ProductCharacteristic characteristic);
    List<ProductCharacteristic> findAllProductCharacteristics();
    Optional<ProductCharacteristic> findProductCharacteristicById(Integer id);
    ProductCharacteristic updateProductCharacteristic(ProductCharacteristic characteristic, Integer id);
   
}
