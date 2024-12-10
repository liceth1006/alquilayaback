package com.alquilaya.service;

import java.util.List;
import java.util.Optional;

import com.alquilaya.entity.ProductCharacteristic;

public interface IProductCharacteristicService {
	ProductCharacteristic saveProductCharacteristic(ProductCharacteristic characteristic);
    List<ProductCharacteristic> findAllProductCharacteristics();
    Optional<ProductCharacteristic> findProductCharacteristicById(Integer id);
    List<ProductCharacteristic> findCharacteristicsByProductId(Integer productId);
    ProductCharacteristic updateProductCharacteristic(ProductCharacteristic characteristic, Integer id);
   
}
