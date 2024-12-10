package com.alquilaya.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alquilaya.dao.IFavoriteDao;
import com.alquilaya.dto.FavoriteProductDTO;
import com.alquilaya.dto.ProductProjectionDTO;
import com.alquilaya.entity.Favorite;

@Service
public class FavoriteService implements IFavoriteService {

	
	
	@Autowired
	IFavoriteDao dao;
	
	@Override
	public Favorite saveFavorite(Favorite favorite) {
        // Validar si ya existe el favorito
		Optional<Favorite> existingFavorites = dao.checkFavorite(
            favorite.getUser().getUserId(),
            favorite.getProduct().getProductId()
        );

        if (!existingFavorites.isEmpty()) {
            throw new IllegalStateException("El producto ya está marcado como favorito.");
        }

        // Guardar el favorito si no existe
        return dao.saveFavorite(favorite);
    }

	@Override
	public void deleteFavorite(Integer id) {
		// TODO Auto-generated method stub
		 dao.deleteFavorite(id);
	}

	@Override
	public List<FavoriteProductDTO> findFavoriteProductsByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return dao.findFavoriteProductsByUserId(userId);
	}

	@Override
	public List<FavoriteProductDTO> findAllFavoriteProducts() {
		// TODO Auto-generated method stub
		return dao.findAllFavoriteProducts();
	}

}
