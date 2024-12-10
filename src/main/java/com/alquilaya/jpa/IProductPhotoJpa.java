package com.alquilaya.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.alquilaya.entity.ProductPhoto;

public interface IProductPhotoJpa extends JpaRepository<ProductPhoto, Integer> {
	List<ProductPhoto> findByProductProductId(Integer productId);
}
