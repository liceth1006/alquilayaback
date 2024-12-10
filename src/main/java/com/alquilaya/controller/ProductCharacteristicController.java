package com.alquilaya.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alquilaya.entity.ProductCharacteristic;
import com.alquilaya.service.IProductCharacteristicService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/")
@Tag(name = "11 - Característica generales de todos los productos", description = "Operaciones relacionadas con las características generales de los productos.")
public class ProductCharacteristicController {

    @Autowired
    private IProductCharacteristicService service;

    @Operation(
        summary = "Obtener todas las características de los productos",
        description = "Este endpoint permite obtener todas las características de los productos."
    )
    @GetMapping(value="public/characteristics-all",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductCharacteristic>> getAllProductCharacteristics() {
        List<ProductCharacteristic> characteristics = service.findAllProductCharacteristics();
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
    @GetMapping(value = "public/Characteristic-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductCharacteristic> getProductCharacteristicById(
        @RequestParam @Parameter(description = "ID de la característica del producto", required = true) Integer id) {
        
        Optional<ProductCharacteristic> characteristic = service.findProductCharacteristicById(id);
        return characteristic.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

   

    @Operation(
        summary = "Crear una nueva característica de producto",
        description = "Este endpoint permite crear una nueva característica para un producto.",
        responses = {
            @ApiResponse(responseCode = "201", description = "Característica de producto creada exitosamente."),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida.")
        }
    )
    @PostMapping(value="admin/characteristics-add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductCharacteristic> createProductCharacteristic(
        @RequestBody @Parameter(description = "Detalles de la nueva característica de producto", required = true) ProductCharacteristic characteristic) {
        
        ProductCharacteristic newCharacteristic = service.saveProductCharacteristic(characteristic);
        return new ResponseEntity<>(newCharacteristic, HttpStatus.CREATED);
    }

    @Operation(
        summary = "Actualizar una característica de producto",
        description = "Este endpoint permite actualizar los detalles de una característica de producto existente mediante su ID.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Característica de producto actualizada exitosamente."),
            @ApiResponse(responseCode = "404", description = "Característica del producto no encontrada.")
        }
    )
    @PutMapping(value = "admin/characteristics-update",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductCharacteristic> updateProductCharacteristic(
        @RequestParam @Parameter(description = "ID de la característica del producto a actualizar", required = true) Integer id,
        @RequestBody @Parameter(description = "Detalles actualizados de la característica de producto", required = true) ProductCharacteristic characteristic) {
        
        ProductCharacteristic updatedCharacteristic = service.updateProductCharacteristic(characteristic, id);
        return new ResponseEntity<>(updatedCharacteristic, HttpStatus.OK);
    }
}
