package com.alquilaya.controller;


import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import com.alquilaya.entity.ProductDetails;
import com.alquilaya.service.IProductDetailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;

@RestController
@Tag(name = "8 - Detalles de Productos", description = "Operaciones relacionadas con los detalles de los productos.")
@RequestMapping("/api/v1/")
public class ProductDetailController {

    @Autowired
    private IProductDetailService service;

    
    @Operation(
    	    summary = "Agregar un nuevo detalle de producto",
    	    description = "Este endpoint permite crear un nuevo detalle de producto en la base de datos.",
    	    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
    	        description = "Objeto que representa los detalles de un nuevo producto, incluyendo información de la dirección, ciudad, departamento, y stock.",
    	        content = @Content(
    	            mediaType = "application/json",
    	            schema = @Schema(
    	            		 example = "{\n" +
    	            	              "  \"address\": \"Calle 123 #45-67\",\n" +
    	            	              "  \"city\": \"Bogotá\",\n" +
    	            	              "  \"department\": \"Cundinamarca\",\n" +
    	            	              "  \"product\": {\n" +
    	            	              "    \"productId\": 2\n" +
    	            	              "  },\n" +
    	            	              "  \"stock\": 10\n" +
    	            	              "}"
    	            )
    	        )
    	    )
    	)
    	@PostMapping(value = "user/add-details", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    	public ResponseEntity<ProductDetails> create(@Valid @RequestBody ProductDetails productDetail) {
    	    ProductDetails newProduct = service.saveProductDetail(productDetail);
    	    return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    	}

  
    @GetMapping(value="public/details-all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtener todos los detalles de productos", description = "Obtiene una lista de todos los detalles de productos.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Detalles de productos obtenidos exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<ProductDetails>> getAllProductDetails() {
        List<ProductDetails> productDetails = service.findAllProductDetails();
        return new ResponseEntity<>(productDetails, HttpStatus.OK);
    }

    @Operation(summary = "Obtener detalle de producto por ID", description = "Obtiene un detalle de producto según su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Detalle de producto obtenido exitosamente"),
        @ApiResponse(responseCode = "404", description = "Detalle de producto no encontrado")
    })
    @GetMapping(value = "public/details-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDetails> getProductDetailById(@RequestParam Integer id) {
        Optional<ProductDetails> productDetail = service.findProductDetailById(id);
        return productDetail.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/public/product-details-id", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtener detalles de productos por ID de producto", description = "Obtiene los detalles de productos asociados a un ID de producto específico.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Detalles de productos obtenidos exitosamente por ID de producto"),
        @ApiResponse(responseCode = "404", description = "Detalles de productos no encontrados para el ID de producto dado")
    })
    public ResponseEntity<List<ProductDetails>> getProductDetailsByProductId(@RequestParam Integer productId) {
        List<ProductDetails> productDetails = service.findProductDetailsByProductId(productId);
        return new ResponseEntity<>(productDetails, HttpStatus.OK);
    }


    @Operation(
    	    summary = "Editar un detalle de producto existente",
    	    description = "Este endpoint permite actualizar los detalles de un producto en la base de datos.",
    	    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
    	        description = "Objeto que representa los nuevos detalles de un producto, incluyendo información de la dirección, ciudad, departamento, y stock.",
    	        content = @Content(
    	            mediaType = "application/json",
    	            schema = @Schema(
    	                example = "{\n" +
    	                          "  \"address\": \"Calle 123 #45-67\",\n" +
    	                          "  \"city\": \"Bogotá\",\n" +
    	                          "  \"department\": \"Cundinamarca\",\n" +
    	                          "  \"product\": {\n" +
    	                          "    \"productId\": 2\n" +
    	                          "  },\n" +
    	                          "  \"stock\": 10\n" +
    	                          "}"
    	            )
    	        )
    	    ),
    	    responses = {
    	        @ApiResponse(
    	            responseCode = "200",
    	            description = "Detalle de producto actualizado correctamente",
    	            content = @Content(mediaType = "application/json")
    	        ),
    	        @ApiResponse(
    	            responseCode = "400",
    	            description = "Solicitud incorrecta o inválida",
    	            content = @Content(mediaType = "application/json")
    	        ),
    	        @ApiResponse(
    	            responseCode = "404",
    	            description = "Producto no encontrado",
    	            content = @Content(mediaType = "application/json")
    	        )
    	    }
    	)
    @PutMapping(value = "/user/update-details", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDetails> updateProductDetail(@RequestParam Integer id, @RequestBody ProductDetails productDetail) {
    	ProductDetails updatedProductDetail = service.updateProductDetail(productDetail, id); 	
        return new ResponseEntity<>(updatedProductDetail, HttpStatus.OK);
    }
}

