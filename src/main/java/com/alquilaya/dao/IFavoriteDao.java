package com.alquilaya.dao;

import java.util.List;
import java.util.Optional;

import com.alquilaya.dto.FavoriteProductDTO;
import com.alquilaya.dto.ProductProjectionDTO;
import com.alquilaya.entity.Favorite;

public interface IFavoriteDao {
	 Favorite saveFavorite(Favorite favorite);	
	 List<FavoriteProductDTO> findAllFavoriteProducts();
	 List<FavoriteProductDTO> findFavoriteProductsByUserId(Integer userId);
	 void deleteFavorite(Integer id);
	 Optional<Favorite>  checkFavorite(Integer userId,Integer productId);
}
