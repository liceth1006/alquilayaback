package com.alquilaya.dao;

import java.util.List;
import java.util.Optional;

import com.alquilaya.entity.ProductPhoto;

public interface IProductPhotoDao {
	ProductPhoto saveProductPhoto(ProductPhoto productPhoto);
    List<ProductPhoto> findAllProductPhotos();
    Optional<ProductPhoto> findProductPhotoById(Integer id);
    List<ProductPhoto> findProductPhotosByProductId(Integer productId);
    ProductPhoto updateProductPhoto(ProductPhoto productPhoto, Integer id);
    
}
