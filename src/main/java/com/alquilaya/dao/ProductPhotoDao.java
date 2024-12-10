package com.alquilaya.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alquilaya.entity.Product;
import com.alquilaya.entity.ProductPhoto;
import com.alquilaya.jpa.IProductPhotoJpa;

import jakarta.persistence.EntityNotFoundException;

@Repository
public class ProductPhotoDao  implements IProductPhotoDao{

	@Autowired
	IProductPhotoJpa jpa;

	@Override
	public ProductPhoto saveProductPhoto(ProductPhoto productPhoto) {
		return jpa.save(productPhoto);
	}

	@Override
	public List<ProductPhoto> findAllProductPhotos() {
		return jpa.findAll();
	}

	@Override
	public Optional<ProductPhoto> findProductPhotoById(Integer id) {
		 return jpa.findById(id);
	}
/*
	@Override
	public List<ProductPhoto> findProductPhotosByProductId(Integer productId) {
		return jpa.findByProductId(productId);
	}
*/
	@Override
	public ProductPhoto updateProductPhoto(ProductPhoto productPhoto, Integer id) {
		if (jpa.existsById(id)) {
			productPhoto.setPhotoId(id);  
            return jpa.save(productPhoto);  
        }
        throw new EntityNotFoundException("Address with ID " + id + " not found");  
    }

	@Override
	public List<ProductPhoto> findProductPhotosByProductId(Integer productId) {
		
		return jpa.findByProductProductId(productId);
	}
	
	

}
