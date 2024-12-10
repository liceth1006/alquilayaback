package com.alquilaya.dao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alquilaya.dto.FavoriteProductDTO;
import com.alquilaya.dto.ProductProjectionDTO;
import com.alquilaya.entity.Favorite;
import com.alquilaya.jpa.IFavoriteJpa;

import jakarta.persistence.EntityNotFoundException;

@Repository
public class FavoriteDao implements IFavoriteDao {

	
	@Autowired
	IFavoriteJpa jpa;
	@Override
	public Favorite saveFavorite(Favorite favorite) {
		return jpa.save(favorite);
	}

	@Override
	public void deleteFavorite(Integer id) {
		 if (jpa.existsById(id)) {
	            jpa.deleteById(id);
	        } else {
	            throw new EntityNotFoundException("Favorite with ID " + id + " not found");
	        }
		
	}

	@Override
	public List<FavoriteProductDTO> findFavoriteProductsByUserId(Integer userId) {
	  

		  if (userId == null) {
	            throw new IllegalArgumentException("El ID de usuario no puede ser nulo.");
	        }

	        return jpa.findProjectedProductsByUserId(userId);
	    }

	@Override
	public List<FavoriteProductDTO> findAllFavoriteProducts() {
		// TODO Auto-generated method stub
		return jpa.findAllFavoriteProducts();
	}

	@Override
	public Optional<Favorite> checkFavorite(Integer userId, Integer productId) {
		// TODO Auto-generated method stub
		return jpa.checkFavorite(userId, productId);
	}


	
	


}
