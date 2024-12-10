package com.alquilaya.service;

import java.util.List;
import java.util.Optional;

import com.alquilaya.dto.FavoriteProductDTO;
import com.alquilaya.dto.ProductProjectionDTO;
import com.alquilaya.entity.Favorite;

public interface IFavoriteService {
	Favorite saveFavorite(Favorite favorite);
	List<FavoriteProductDTO> findAllFavoriteProducts();
	 void deleteFavorite(Integer id);
	 List<FavoriteProductDTO> findFavoriteProductsByUserId(Integer userId);
}