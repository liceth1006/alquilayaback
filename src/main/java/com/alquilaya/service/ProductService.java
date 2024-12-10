package com.alquilaya.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alquilaya.dao.IProductDao;
import com.alquilaya.dto.ProductProjectionDTO;
import com.alquilaya.entity.Category;
import com.alquilaya.entity.Product;
import com.alquilaya.entity.ProductPhoto;
import com.alquilaya.entity.Profile;
import com.alquilaya.entity.User;

@Service
public class ProductService implements IProductService {

	
	@Autowired
	IProductDao dao;


	public List<ProductProjectionDTO> findProductsByNameAndCity(String name, String city) {
        return dao.findProductsByNameAndCity(name, city);
    }

	@Override
	public Product updateProduct(Product product, Integer id) {

		return dao.updateProduct(product, id);
	}


	public List<ProductProjectionDTO> findAllProjectedProducts() {
        return dao.findAllProjectedProducts(); 
    }

	@Override
	public Optional<ProductProjectionDTO> findProjectedProductsById(Integer id) {
		
		return dao.findProjectedProductsById(id);
	}

	@Override
	public List<ProductProjectionDTO> filterProducts(String categoryName, String supplierName,
			String productCondition) {
		 return dao.filterProducts(categoryName, supplierName, productCondition);
	}

	@Override
	public List<ProductProjectionDTO> findProjectedProductsByCategoryId(Integer categoryId) {
		return dao.findProjectedProductsByCategoryId(categoryId);
	}

	@Override
	public Product createProduct(Product product) {      
	        return dao.createProduct(product);
	}

	@Override
	public List<ProductProjectionDTO> findProductsByUserId(Integer userId) {
		
		return dao.findProductsByUserId(userId);
	}



	
}
