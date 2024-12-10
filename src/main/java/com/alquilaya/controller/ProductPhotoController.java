package com.alquilaya.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alquilaya.entity.ProductDetails;
import com.alquilaya.entity.ProductPhoto;
import com.alquilaya.service.IProductPhotoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/")
@Tag(name = "9 - Fotos de los productos", description = "Operaciones relacionadas con las fotos de los productos.")
@AllArgsConstructor
public class ProductPhotoController {

	@Autowired
	IProductPhotoService service;

	@Operation(
	        summary = "Obtener todas las fotos de productos",
	        description = "Este endpoint permite obtener todas las fotos de los productos disponibles en el sistema, sin necesidad de un `productId` específico."
	    )
	@GetMapping(value="public/photo-all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductPhoto>> getProductPhotosByProductId(
			@RequestParam @Parameter(description = "ID del producto cuyo fotos serán consultadas.", required = true) Integer productId) {

		List<ProductPhoto> productPhotos = service.findProductPhotosByProductId(productId);
		if (productPhotos.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(productPhotos, HttpStatus.OK);
		}
	}

	@GetMapping(value = "public/photo-id", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			summary = "Obtener fotos del producto segun el id del producto", 
			description = "Este endpoint permite obtener todas las fotos asociadas a un producto utilizando su ID."
			)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Fotos de productos obtenidas con éxito."),
        @ApiResponse(responseCode = "404", description = "No se encontraron fotos de productos.")
    })
    public ResponseEntity<List<ProductPhoto>> getAllProductPhotos() {
        List<ProductPhoto> productPhotos = service.findAllProductPhotos();
        if (productPhotos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(productPhotos, HttpStatus.OK);
        }
    }
	
	@Autowired
    HttpServletRequest request;
	
	
	
    @GetMapping(value = "/public/photo-productId", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtener detalles de productos por ID de producto", description = "Obtiene los detalles de productos asociados a un ID de producto específico.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Detalles de productos obtenidos exitosamente por ID de producto"),
        @ApiResponse(responseCode = "404", description = "Detalles de productos no encontrados para el ID de producto dado")
    })
    public ResponseEntity<List<ProductPhoto>> getProductDetailsByProductId(@RequestParam Integer productId) {
        List<ProductPhoto> productPhoto = service.findProductPhotoByProductId(productId);
        return new ResponseEntity<>(productPhoto, HttpStatus.OK);
    }
	
	@Operation(
	        summary = "Subir una foto para un producto",
	        description = "Este endpoint permite subir una foto asociada a un producto específico utilizando su ID."
	        )
	@PostMapping("/photo-add")
	public Map<String, String> uploadPhoto(@RequestParam("file") MultipartFile multipartfile, @RequestParam Integer id) {
		String path = service.savePhoto(multipartfile, id);
		String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
		String url = ServletUriComponentsBuilder
				.fromHttpUrl(host)
    			.path("/api/v1/")
    			.path(path)
    			.toUriString();
		
		return Map.of("url", url);
	}
	
	@Operation(
		    summary = "Actualizar foto de un producto",
		    description = "Este endpoint permite actualizar la foto de un producto existente utilizando su ID."
		)
	@PutMapping("/user/photo-update")
	public Map<String, String> updatePhoto(@RequestParam("file") MultipartFile multipartfile, @RequestParam Integer id) {
		String path = service.updatePhoto(multipartfile, id);
		String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
		String url = ServletUriComponentsBuilder
				.fromHttpUrl(host)
    			.path("/api/v1/")
    			.path(path)
    			.toUriString();
		
		return Map.of("url", url);
	}
	
	@GetMapping("{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) throws IOException {
    	Resource file = service.loadAsResource(filename);
    	String contentType = Files.probeContentType(file.getFile().toPath());
    	
    	return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, contentType).body(file);
    }
	

	/*
	 * @GetMapping(value = "/product/{productId}", produces =
	 * MediaType.APPLICATION_JSON_VALUE) public ResponseEntity<List<ProductPhoto>>
	 * getProductPhotosByProductId(@PathVariable Integer productId) {
	 * List<ProductPhoto> productPhotos =
	 * service.findProductPhotosByProductId(productId); return new
	 * ResponseEntity<>(productPhotos, HttpStatus.OK); }
	 */


}
