package com.alquilaya.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alquilaya.dao.IProductDao;
import com.alquilaya.dao.IProductPhotoDao;
import com.alquilaya.entity.Product;
import com.alquilaya.entity.ProductPhoto;

import jakarta.annotation.PostConstruct;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ProductPhotoService implements IProductPhotoService {

	@Autowired
	IProductPhotoDao dao;

	@Autowired
	IProductDao daoProduct;
	
	@Value("${media.location}")
	private String mediaLocation;
	private Path rootLocation;

	@Override
	@PostConstruct
	public void init() throws IOException {
		rootLocation = Paths.get(mediaLocation);
		Files.createDirectories(rootLocation);	
	}

	@Override
	public String savePhoto(MultipartFile file, Integer productId) {
		try {
			if (file.isEmpty()) {
				throw new RuntimeException("Failed to store empty file.");
			}
			
			Product product = daoProduct.findProductById(productId)
					.orElseThrow(() -> new RuntimeException("Product not found"));
			
			String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
			Path destinationFile = rootLocation.resolve(Paths.get(filename)).normalize().toAbsolutePath();
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
			}
			
			ProductPhoto productPhoto = new ProductPhoto();
			
			productPhoto.setPhotoUrl("http://localhost:8080/api/v1/"+filename);
			productPhoto.setIsPrimary((byte) 1);
			productPhoto.setProduct(product);
			productPhoto.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
			productPhoto.setUpdatedAt(new java.sql.Timestamp(System.currentTimeMillis()));

			// Save the product photo in the database
			dao.saveProductPhoto(productPhoto);
			return filename;
					
		} catch (IOException e) {
			throw new RuntimeException("Failed to store file.", e);
		}
	}
	
	@Override
	public Optional<ProductPhoto> findProductPhotoById(Integer id) {
		return dao.findProductPhotoById(id);
	}

	@Override
	public List<ProductPhoto> findProductPhotosByProductId(Integer productId) {
		return dao.findProductPhotosByProductId(productId);
	}
	
	@Override
	public String updatePhoto(MultipartFile file, Integer id) {
		try {
			if (file.isEmpty()) {
				throw new RuntimeException("Failed to store empty file.");
			}
			
			Optional<ProductPhoto> existingPhotoOptional = dao.findProductPhotoById(id);
	        if (existingPhotoOptional.isEmpty()) {
	            throw new RuntimeException("Product photo not found");
	        }
			
			String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
			Path destinationFile = rootLocation.resolve(Paths.get(filename)).normalize().toAbsolutePath();
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
			}
			
			ProductPhoto existingPhoto = existingPhotoOptional.get();
			
			existingPhoto.setPhotoUrl("http://localhost:8080/api/v1/"+filename);
			existingPhoto.setIsPrimary((byte) 0);
			existingPhoto.setUpdatedAt(new java.sql.Timestamp(System.currentTimeMillis()));

			dao.updateProductPhoto(existingPhoto, id);
			return filename;
					
		} catch (IOException e) {
			throw new RuntimeException("Failed to update file.", e);
		}
	}

	@Override
	public List<ProductPhoto> findAllProductPhotos() {
		return dao.findAllProductPhotos();
	}
	
	@Override
	public Resource loadAsResource(String filename) {
		try {
			Path file = rootLocation.resolve(filename);
			Resource resource = new UrlResource((file.toUri()));
			
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read file: " + filename);
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Could not read file: " + filename);
		}
	}

	@Override
	public List<ProductPhoto> findProductPhotoByProductId(Integer productId) {
		// TODO Auto-generated method stub
		return dao.findProductPhotosByProductId(productId);
	}
}
