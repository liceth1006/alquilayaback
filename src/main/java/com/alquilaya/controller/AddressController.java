package com.alquilaya.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.v3.oas.annotations.media.Schema;
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

import com.alquilaya.entity.Address;
import com.alquilaya.service.IAddressService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/")
@Tag(name = "5 - Direcciones de Usuarios", description = "Operaciones relacionadas con el registro de las diferentes direcciones")
public class AddressController {

    @Autowired
    IAddressService service;
    
    @Operation(
			summary = "Consulta todas las direcciones de la base de datos ",
			description = "Accede al registro completo de todas las direcciones disponibles en la base de datos."
	)
    @ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Ok"),
			@ApiResponse(responseCode = "400",description = "Error de Acceso", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
    @GetMapping(value="admin/address-all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Address>> getAllAddresses() {
		List<Address> addresses = service.findAllAddresses();
		return new ResponseEntity<>(addresses, HttpStatus.OK);
	}
    
    @Operation(
			summary = "Consulta cada una de las direcciones de la base de datos por ID",
			description = "Accede al registro de las direcciones disponibles en la base de datos por ID."
	)
    @ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Ok"),
			@ApiResponse(responseCode = "400",description = "Error de Acceso", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
    @GetMapping(value = "user/address-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Address> getAddressById(@RequestParam Integer id) {
        Optional<Address> address = service.findAddressById(id);
        return address.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(
    	    summary = "Agregar una nueva dirección del usuario",
    	    description = "Este endpoint permite crear una nueva dirección de usuario en la base de datos.",
    	    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
    	        description = "Objeto que representa los detalles de una nueva dirección, incluyendo información de la dirección, ciudad y departamento.",
    	        content = @Content(
    	            mediaType = "application/json",
    	            schema = @Schema(
    	                example = "{\n" +
    	                         "  \"address\": \"Calle 123 #45-67\",\n" +
    	                         "  \"city\": \"Bogotá\",\n" +
    	                         "  \"department\": \"Cundinamarca\"\n" +
    	                         "}"
    	            )
    	        )
    	    )
    	)
    
    @PostMapping(value="user/address-add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Address> createAddress(@Valid @RequestBody Address address) {
        Address newAddress = service.saveAddress(address);
        return new ResponseEntity<>(newAddress, HttpStatus.CREATED);
    }
    
  @Operation(
		    summary = "Actualizar una dirección existente del usuario",
		    description = "Este endpoint permite actualizar los detalles de una dirección de usuario ya existente en la base de datos.",
		    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
		        description = "Objeto que representa los detalles de la dirección a actualizar, incluyendo la dirección, ciudad y departamento.",
		        content = @Content(
		            mediaType = "application/json",
		            schema = @Schema(
		                example = "{\n" +
		                         "  \"id\": 1,\n" +
		                         "  \"address\": \"Carrera 45 #32-10\",\n" +
		                         "  \"city\": \"Medellín\",\n" +
		                         "  \"department\": \"Antioquia\"\n" +
		                         "}"
		            )
		        )
		    )
		)
  
    @PutMapping(value = "user/address-update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Address> updateAddress(@Valid @RequestParam Integer id, @RequestBody Address address) {
        Address updatedAddress = service.updateAddress(address, id);
        return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
    }
}
