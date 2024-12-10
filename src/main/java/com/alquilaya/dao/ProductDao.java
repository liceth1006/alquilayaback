package com.alquilaya.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alquilaya.dto.ProductProjectionDTO;
import com.alquilaya.entity.Product;
import com.alquilaya.jpa.IProductJpa;

import jakarta.persistence.EntityNotFoundException;

@Repository
public class ProductDao implements IProductDao {

	@Autowired
	IProductJpa jpa;



	@Override
	public Optional<Product> findProductById(Integer id) {
		return jpa.findById(id);
	}
	
	public List<ProductProjectionDTO> findProductsByNameAndCity(String name, String city) {
        return jpa.findProductsByNameAndCity(name, city);
    }


	@Override
	public Product updateProduct(Product product, Integer id) {
	    if (jpa.existsById(id)) {
	        // Obtener el producto existente
	        Product existingProduct = jpa.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Product with ID " + id + " not found"));

	        // Conservar campos inmutables
	        product.setCreatedAt(existingProduct.getCreatedAt());

	        // Asignar la fecha y hora actual al campo updated_at
	        product.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

	        // Asegurarse de que el ID sea el correcto
	        product.setProductId(id);

	        // Guardar el producto actualizado
	        return jpa.save(product);
	    }
	    throw new EntityNotFoundException("Product with ID " + id + " not found");
	}


	
    // Métodos para llamar la proyección de productos
	@Override
    public List<ProductProjectionDTO> findAllProjectedProducts() {
        return jpa.findAllProjectedProducts(); 
    }

	@Override
	public Optional<ProductProjectionDTO> findProjectedProductsById(Integer id) {
		
		return jpa.findProjectedProductById(id);
	}

	@Override
	public List<ProductProjectionDTO> filterProducts(String categoryName, String supplierName,
			String productCondition) {
		
		return jpa.findProductsByFilters(categoryName, supplierName, productCondition);
	}

	@Override
	public List<ProductProjectionDTO> findProjectedProductsByCategoryId(Integer categoryId) {
		
		return jpa.findProductsByCategoryId(categoryId);
	}

	@Override
	public Product createProduct(Product product) {
		return jpa.save(product);
	}

	@Override
	public List<ProductProjectionDTO> findProductsByUserId(Integer userId) {
		
		return jpa.findProjectedProductsByUserId(userId);
	}


}
