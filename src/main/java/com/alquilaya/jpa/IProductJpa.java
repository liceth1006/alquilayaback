package com.alquilaya.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alquilaya.dto.FavoriteProductDTO;
import com.alquilaya.dto.ProductProjectionDTO;
import com.alquilaya.entity.Product;

public interface IProductJpa extends JpaRepository<Product, Integer> {

	@Query("SELECT new com.alquilaya.dto.ProductProjectionDTO(" +
	           "p.productId, p.name, p.description, p.price, p.isAvailable, " +
	           "p.productCondition, p.status, " +  
	           "c.name, c.categoryId, " +  
	           "CONCAT(s.firstName, ' ', s.lastName), " +  
	           "pp.photoUrl) " +
	           "FROM Product p " +
	           "JOIN p.productDetails pd " +
	           "JOIN p.category c " +
	           "JOIN p.supplier u " +
	           "JOIN u.profile s " +  
	           "LEFT JOIN p.productPhotos pp ON pp.isPrimary = 1 " +
	           "WHERE p.name LIKE %:name% " +
	           "AND pd.city LIKE %:city%")
	List<ProductProjectionDTO> findProductsByNameAndCity(String name, String city);

	@Query("SELECT p FROM Product p WHERE p.category.id = ?1")
	 List<Product> findByCategoryId(Integer categoryId); 
	
	@Query("SELECT new com.alquilaya.dto.ProductProjectionDTO(" +
		       "p.productId, p.name, p.description, p.price, p.isAvailable, " +
		       "p.productCondition, p.status, " +
		       "c.name, c.categoryId, " +
		       "COALESCE(CONCAT(s.firstName, ' ', s.lastName), 'Anónimo'), " + 
		       "pp.photoUrl) " +
		       "FROM Product p " +
		       "JOIN p.category c " +
		       "LEFT JOIN p.supplier u " +  
		       "LEFT JOIN u.profile s " +  
		       "LEFT JOIN p.productPhotos pp ON pp.isPrimary = 1")
		List<ProductProjectionDTO> findAllProjectedProducts();


	@Query("SELECT new com.alquilaya.dto.ProductProjectionDTO(" +
		       "p.productId, p.name, p.description, p.price, p.isAvailable, " +
		       "p.productCondition, p.status, " +  
		       "c.name, c.categoryId, " + 
		       "COALESCE(CONCAT(s.firstName, ' ', s.lastName), 'Anónimo'), " + 
		       "pp.photoUrl) " +
		       "FROM Product p " +
		       "JOIN p.category c " +
		       "LEFT JOIN p.supplier u " +  
		       "LEFT JOIN u.profile s " + 
		       "LEFT JOIN p.productPhotos pp ON pp.isPrimary = 1 " +
		       "WHERE p.productId = ?1")
		Optional<ProductProjectionDTO> findProjectedProductById(Integer id);
	
	@Query("SELECT new com.alquilaya.dto.ProductProjectionDTO(p.productId, p.name, p.description, p.price, p.isAvailable, " +
		       "p.productCondition, p.status, c.name, c.categoryId, CONCAT(s.firstName, ' ', s.lastName), pp.photoUrl) " +
		       "FROM Product p " +
		       "JOIN p.category c " +
		       "JOIN p.supplier u " +
		       "JOIN u.profile s " +
		       "LEFT JOIN p.productPhotos pp ON pp.isPrimary = 1 " +
		       "WHERE (:categoryName IS NULL OR c.name = :categoryName) " +
		       "AND (:supplierName IS NULL OR CONCAT(s.firstName, ' ', s.lastName) = :supplierName) " +
		       "AND (:productCondition IS NULL OR p.productCondition = :productCondition)")
		List<ProductProjectionDTO> findProductsByFilters(@Param("categoryName") String categoryName,
		                                                 @Param("supplierName") String supplierName,
		                                                 @Param("productCondition") String productCondition);

	
	@Query("SELECT new com.alquilaya.dto.ProductProjectionDTO(" +
		       "p.productId, p.name, p.description, p.price, p.isAvailable, " +
		       "p.productCondition, p.status, " +  
		       "c.name, c.categoryId, " +  
		       "COALESCE(CONCAT(s.firstName, ' ', s.lastName), 'Anónimo'), " + 
		       "pp.photoUrl) " +  
		       "FROM Product p " +
		       "JOIN p.category c " +
		       "LEFT JOIN p.supplier u " +  
		       "LEFT JOIN u.profile s " +  
		       "LEFT JOIN p.productPhotos pp ON pp.isPrimary = 1 " +
		       "WHERE c.id = ?1")
		List<ProductProjectionDTO> findProductsByCategoryId(Integer categoryId);

	
	
	@Query("SELECT new com.alquilaya.dto.ProductProjectionDTO(" +
		       "p.productId, p.name, p.description, p.price, p.isAvailable, " +
		       "p.productCondition, p.status, " +  
		       "c.name, c.categoryId, " +  
		       "COALESCE(CONCAT(s.firstName, ' ', s.lastName), 'Anónimo'), " + 
		       "pp.photoUrl) " +  
		       "FROM Product p " +
		       "JOIN p.category c " +
		       "LEFT JOIN p.supplier u " +  
		       "LEFT JOIN u.profile s " +  
		       "LEFT JOIN p.productPhotos pp ON pp.isPrimary = 1 " +
		       "WHERE u.id = ?1")
		List<ProductProjectionDTO> findProjectedProductsByUserId(Integer userId);







}
