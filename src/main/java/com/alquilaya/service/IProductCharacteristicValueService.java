package com.alquilaya.service;

import java.util.List;
import java.util.Optional;

import com.alquilaya.entity.ProductCharacteristicValue;

public interface IProductCharacteristicValueService {

	ProductCharacteristicValue saveProductCharacteristic(ProductCharacteristicValue characteristic);
    List<ProductCharacteristicValue> findAllProductCharacteristics();
    Optional<ProductCharacteristicValue> findProductCharacteristicById(Integer id);
   List<ProductCharacteristicValue> findCharacteristicsByProductId(Integer productId);
    ProductCharacteristicValue updateProductCharacteristic(ProductCharacteristicValue characteristic, Integer id);
}
