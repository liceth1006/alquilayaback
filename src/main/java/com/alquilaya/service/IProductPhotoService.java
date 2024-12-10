package com.alquilaya.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.alquilaya.entity.ProductDetails;
import com.alquilaya.entity.ProductPhoto;

public interface IProductPhotoService {
    void init() throws IOException;
    String savePhoto(MultipartFile file, Integer productId);
    Optional<ProductPhoto> findProductPhotoById(Integer id);
    List<ProductPhoto> findProductPhotosByProductId(Integer productId);
    String updatePhoto(MultipartFile file, Integer id);
    List<ProductPhoto> findAllProductPhotos();
    Resource loadAsResource(String filename);
    List<ProductPhoto> findProductPhotoByProductId(Integer productId);
}
