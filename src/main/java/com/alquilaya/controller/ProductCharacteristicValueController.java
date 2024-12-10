package com.alquilaya.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alquilaya.entity.ProductCharacteristicValue;
import com.alquilaya.service.IProductCharacteristicValueService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/v1/")
@Tag(name = "10 - Característica de productos value ", description = "Operaciones relacionadas con las características de los productos.")
public class ProductCharacteristicValueController {
	
	@Autowired
    private IProductCharacteristicValueService service;

    @Operation(
        summary = "Obtener todas las características de los productos",
        description = "Este endpoint permite obtener todas las características de los productos."
    )
    @GetMapping(value="public/all-charact",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductCharacteristicValue>> getAllProductCharacteristics() {
        List<ProductCharacteristicValue> characteristics = service.findAllProductCharacteristics();
        return new ResponseEntity<>(characteristics, HttpStatus.OK);
    }

    @Operation(
        summary = "Obtener una característica de producto por ID",
        description = "Este endpoint permite obtener los detalles de una característica de producto mediante su ID.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Característica del producto encontrada."),
            @ApiResponse(responseCode = "404", description = "Característica del producto no encontrada.")
        }
    )
    @GetMapping(value="public/id-charact",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductCharacteristicValue> getProductCharacteristicById(
        @RequestParam @Parameter(description = "ID de la característica del producto", required = true) Integer id) {
        
        Optional<ProductCharacteristicValue> characteristic = service.findProductCharacteristicById(id);
        return characteristic.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(
        summary = "Obtener características de un producto por ID de producto",
        description = "Este endpoint permite obtener las características asociadas a un producto específico mediante su ID.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Características del producto encontradas."),
            @ApiResponse(responseCode = "404", description = "No se encontraron características para el producto.")
        }
    )
    @GetMapping(value = "public/charact-productId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductCharacteristicValue>> getCharacteristicsByProductId(
        @RequestParam @Parameter(description = "ID del producto para el cual obtener las características", required = true) Integer productId) {
        
        List<ProductCharacteristicValue> characteristics = service.findCharacteristicsByProductId(productId);
        return new ResponseEntity<>(characteristics, HttpStatus.OK);
    }

    @Operation(
    	    summary = "Agregar una nueva característica a un producto",
    	    description = "Permite crear una nueva característica asociada a un producto específico en la base de datos.",
    	    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
    	        description = "Objeto que representa la nueva característica de un producto, incluyendo su valor, el ID de la característica y el ID del producto.",
    	        content = @Content(
    	            mediaType = "application/json",
    	            schema = @Schema(
    	                example = "{\n" +
    	                          "  \"value\": \"rojo\",\n" +
    	                          "  \"productCharacteristic\": {\n" +
    	                          "    \"characteristicId\": 2\n" +
    	                          "  },\n" +
    	                          "  \"product\": {\n" +
    	                          "    \"productId\": 3\n" +
    	                          "  }\n" +
    	                          "}"
    	            )
    	        )
    	    ),
    	    responses = {
    	        @ApiResponse(responseCode = "201", description = "Característica creada exitosamente."),
    	        @ApiResponse(responseCode = "400", description = "Solicitud inválida.")
    	    }
    	)

    @PostMapping(value="user/add-charact", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductCharacteristicValue> createProductCharacteristic(
        @RequestBody @Parameter(description = "Detalles de la nueva característica de producto", required = true) ProductCharacteristicValue characteristic) {      
    	ProductCharacteristicValue newCharacteristic = service.saveProductCharacteristic(characteristic);
        return new ResponseEntity<>(newCharacteristic, HttpStatus.CREATED);
    }

    
    @Operation(
    	    summary = "Actualizar una característica de producto",
    	    description = "Permite actualizar los detalles de una característica de producto existente en la base de datos mediante su ID.",
    	    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
    	        description = "Objeto que contiene los nuevos detalles de la característica de producto, como el valor, ID de la característica y ID del producto.",
    	        content = @Content(
    	            mediaType = "application/json",
    	            schema = @Schema(
    	                example = "{\n" +
    	                          "  \"value\": \"verde\",\n" +
    	                          "  \"productCharacteristic\": {\n" +
    	                          "    \"characteristicId\": 1\n" +
    	                          "  },\n" +
    	                          "  \"product\": {\n" +
    	                          "    \"productId\": 2\n" +
    	                          "  }\n" +
    	                          "}"
    	            )
    	        )
    	    ),
    	    responses = {
    	        @ApiResponse(responseCode = "200", description = "Característica actualizada exitosamente."),
    	        @ApiResponse(responseCode = "404", description = "Característica no encontrada.")
    	    }
    	)
    @PutMapping(value="user/update-charact",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductCharacteristicValue> updateProductCharacteristic(
        @RequestParam @Parameter(description = "ID de la característica del producto a actualizar", required = true) Integer id,
       @Valid @RequestBody @Parameter(description = "Detalles actualizados de la característica de producto", required = true) ProductCharacteristicValue characteristic) {
        
    	ProductCharacteristicValue updatedCharacteristic = service.updateProductCharacteristic(characteristic, id);
        return new ResponseEntity<>(updatedCharacteristic, HttpStatus.OK);
    }
	
}
