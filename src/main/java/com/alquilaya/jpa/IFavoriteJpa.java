package com.alquilaya.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alquilaya.dto.FavoriteProductDTO;
import com.alquilaya.dto.ProductProjectionDTO;
import com.alquilaya.entity.Favorite;

public interface IFavoriteJpa extends JpaRepository<Favorite, Integer>  {
	
	@Query("SELECT f FROM Favorite f WHERE f.user.userId = :userId AND f.product.productId = :productId")
	Optional<Favorite> checkFavorite(@Param("userId") Integer userId, @Param("productId") Integer productId);

	
	@Query("SELECT new com.alquilaya.dto.FavoriteProductDTO(" +
	           "p.productId, p.name, p.description, p.price, p.isAvailable, " +
	           "p.productCondition, p.status, " +
	           "c.name, " +
	           "COALESCE(CONCAT(s.firstName, ' ', s.lastName), 'Anónimo'), " +  
	           "pp.photoUrl, " +
	           "f.favoriteId, " +
	           "f.user.userId) " +
	           "FROM Favorite f " +
	           "JOIN f.product p " +
	           "JOIN p.category c " +
	           "LEFT JOIN p.supplier u " +  
	           "LEFT JOIN u.profile s " +  
	           "LEFT JOIN p.productPhotos pp ON pp.isPrimary = 1 " +
	           "WHERE f.user.userId = ?1")
	List<FavoriteProductDTO> findProjectedProductsByUserId(Integer userId);

	
	@Query("SELECT new com.alquilaya.dto.FavoriteProductDTO(" +
	           "p.productId, p.name, p.description, p.price, p.isAvailable, " +
	           "p.productCondition, p.status, " +
	           "c.name, " + 
	           "COALESCE(CONCAT(s.firstName, ' ', s.lastName), 'Anónimo'), " +  // Se coloca 'Anónimo' cuando no hay nombre
	           "pp.photoUrl, " +
	           "f.favoriteId, " +
	           "f.user.userId) " +
	           "FROM Favorite f " +
	           "JOIN f.product p " +
	           "JOIN p.category c " +
	           "LEFT JOIN p.supplier u " + 
	           "LEFT JOIN u.profile s " +  
	           "LEFT JOIN p.productPhotos pp ON pp.isPrimary = 1")
	List<FavoriteProductDTO> findAllFavoriteProducts();


	


}

