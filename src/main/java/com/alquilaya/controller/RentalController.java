package com.alquilaya.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alquilaya.entity.Rental;
import com.alquilaya.service.IRentalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/rentals")
@Tag(name = "14 - Rental", description = "Operaciones relacionadas con las rentas")
public class RentalController {

    @Autowired
    IRentalService service;

    @Operation(
			summary = "Obtiene todas las renteas",
			description = "Obtiene todas las rentas que se encuentren registradas en la base de datos"
	)
    @ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Operacion exitosa"),
			@ApiResponse(responseCode = "403",description = "Acceso denegado", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
    @GetMapping(value="public/rental-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Rental>> getAllRentals() {
        List<Rental> rentals = service.findAllRentals();
        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }

    @Operation(
			summary = "Obtiene un detalle especificos de una renta",
			description = "Obtiene un detalle de una renta, buscandolo por su id"
	)
    @ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Operacion exitosa"),
			@ApiResponse(responseCode = "403",description = "Acceso denegado", content = {@Content()}),
			@ApiResponse(responseCode = "404",description = "No encontrado", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
    @GetMapping(value = "public/rental-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Rental> getRentalById(@RequestParam Integer id) {
        Optional<Rental> rental = service.findRentalById(id);
        return rental.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(
			summary = "Obtiene las rentas de un proveedor",
			description = "Obtiene todas las rentas de un proveedor, buscandolas por el id de este"
	)
    @ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Operacion exitosa"),
			@ApiResponse(responseCode = "403",description = "Acceso denegado", content = {@Content()}),
			@ApiResponse(responseCode = "404",description = "No encontrado", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
    @GetMapping(value = "/user/supplierId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Rental>> getRentalsByUserId(@RequestParam Integer supplierId) {
        List<Rental> rentals = service.findRentalsBySupplierId(supplierId);
        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }

    @Operation(
			summary = "Obtiene las rentas de un producto",
			description = "Obtiene todas las rentas de un producto, buscandolas por el id de este"
	)
    @ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Operacion exitosa"),
			@ApiResponse(responseCode = "403",description = "Acceso denegado", content = {@Content()}),
			@ApiResponse(responseCode = "404",description = "No encontrado", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
    @GetMapping(value = "/product/productId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Rental>> getRentalsByProductId(@RequestParam Integer productId) {
        List<Rental> rentals = service.findRentalsByProductId(productId);
        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }

    @PostMapping(value="/adm/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Rental> createRental(@RequestBody Rental rental) {
        Rental newRental = service.saveRental(rental);
        return new ResponseEntity<>(newRental, HttpStatus.CREATED);
    }

    @PutMapping(value = "/user/id", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Rental> updateRental(@RequestParam Integer id, @RequestBody Rental rental) {
        Rental updatedRental = service.updateRental(rental, id);
        return new ResponseEntity<>(updatedRental, HttpStatus.OK);
    }
}
