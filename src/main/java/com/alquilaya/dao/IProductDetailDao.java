package com.alquilaya.dao;

import java.util.List;
import java.util.Optional;


import com.alquilaya.entity.ProductDetails;

public interface IProductDetailDao {
	ProductDetails saveProductDetail(ProductDetails productDetails);
    List<ProductDetails> findAllProductDetails();
    Optional<ProductDetails> findProductDetailById(Integer id);
   List<ProductDetails> findProductDetailsByProductId(Integer productId);
   ProductDetails updateProductDetail(ProductDetails productDetail, Integer id);
    
}
