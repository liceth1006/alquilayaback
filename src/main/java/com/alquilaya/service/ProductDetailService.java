package com.alquilaya.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alquilaya.dao.IProductDetailDao;
import com.alquilaya.entity.ProductDetails;

@Service
public class ProductDetailService implements IProductDetailService {

	@Autowired
	IProductDetailDao dao;
	
	
	@Override
	public ProductDetails saveProductDetail(ProductDetails productDetail) {
		
		return  dao.saveProductDetail(productDetail);
	}
	

	@Override
	public List<ProductDetails> findAllProductDetails() {
		
		return dao.findAllProductDetails();
	}

	@Override
	public Optional<ProductDetails> findProductDetailById(Integer id) {
		
		return dao.findProductDetailById(id);
	}

	@Override
	public List<ProductDetails> findProductDetailsByProductId(Integer productId) {
		
		return dao.findProductDetailsByProductId(productId);
	}

	@Override
	public ProductDetails updateProductDetail(ProductDetails productDetail, Integer id) {
		
		return dao.updateProductDetail(productDetail, id);
	}

}
