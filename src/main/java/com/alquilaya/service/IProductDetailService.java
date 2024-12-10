package com.alquilaya.service;

import java.util.List;
import java.util.Optional;


import com.alquilaya.entity.ProductDetails;

public interface IProductDetailService {
	ProductDetails saveProductDetail(ProductDetails productDetail);
    List<ProductDetails> findAllProductDetails();
    Optional<ProductDetails> findProductDetailById(Integer id);
    List<ProductDetails> findProductDetailsByProductId(Integer productId);
    ProductDetails updateProductDetail(ProductDetails productDetail, Integer id);
    
}
