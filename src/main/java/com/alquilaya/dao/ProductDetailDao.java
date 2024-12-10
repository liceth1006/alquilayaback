package com.alquilaya.dao;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.alquilaya.entity.ProductDetails;
import com.alquilaya.jpa.IProductDetailJpa;


import jakarta.persistence.EntityNotFoundException;

@Repository
public class ProductDetailDao implements IProductDetailDao {

	@Autowired
	IProductDetailJpa jpa;
	
	
	
	@Override
	public ProductDetails saveProductDetail(ProductDetails productDetails) {
		
		return jpa.save(productDetails);
		
	}

	@Override
	public List<ProductDetails> findAllProductDetails() {
		return jpa.findAll();
	}

	@Override
	public Optional<ProductDetails> findProductDetailById(Integer id) {
		 return jpa.findById(id);
	}

	@Override
	public List<ProductDetails> findProductDetailsByProductId(Integer productId) {
		return jpa.findByProduct_ProductId(productId);
	}

	@Override
	public ProductDetails updateProductDetail(ProductDetails productDetail, Integer id) {
	    if (jpa.existsById(id)) {
	        productDetail.setProductDetailsId(id);
	        
	        // No modificar el campo created_at
	        productDetail.setCreatedAt(null);  // O simplemente no seteándolo
	        
	        return jpa.save(productDetail);  
	    }
	    throw new EntityNotFoundException("Address with ID " + id + " not found");
	}


}
