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

import com.alquilaya.entity.Phone;
import com.alquilaya.service.IPhoneService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "6 - Telefonos de usuarios", description = "Operaciones relacionadas con la actividad del telefono de los usuarios.")
public class PhoneController {

    @Autowired
    IPhoneService service;
    
    @Operation(
			summary = "Consultar numeros de telefonos de los diferentes usuarios",
			description = "Accede al registro completo de todos los numeros de telefonos disponibles en la base de datos."
	)
    @ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Ok"),
			@ApiResponse(responseCode = "400",description = "Error de Acceso", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
    @GetMapping(value="admin/phone-all",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Phone>> getAllPhones() {
        List<Phone> phones = service.findAllPhones();
        return new ResponseEntity<>(phones, HttpStatus.OK);
    }

    @Operation(
			summary = "Consultar numeros de telefonos en especifico",
			description = "Accede al registro unico del numero de telefono disponibles en la base de datos por ID."
	)
    @ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Ok"),
			@ApiResponse(responseCode = "400",description = "Error de Acceso", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
    @GetMapping(value = "user/phone-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Phone> getPhoneById(@RequestParam Integer id) {
        Optional<Phone> phone = service.findPhoneById(id);
        return phone.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(
			summary = "Obtener numeros de telefonos validos",
			description = "Accede al registro de numeros de telefonos validos en  la base de datos."
	)
    @ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Ok"),
			@ApiResponse(responseCode = "400",description = "Error de Acceso", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
    @GetMapping(value = "user/phone-userId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Phone>> getPhonesByUserId(@RequestParam Integer userId) {
        List<Phone> phones = service.findPhonesByUserId(userId);
        return new ResponseEntity<>(phones, HttpStatus.OK);
    }

    @Operation(
    	    summary = "Agregar números de teléfono en la base de datos",
    	    description = "Permite agregar nuevos números de teléfono en la base de datos.",
    	    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
    	        description = "Objeto que representa un número de teléfono, incluyendo el número, tipo, si es primario y el perfil asociado.",
    	        content = @Content(
    	            mediaType = "application/json",
    	            schema = @Schema(
    	                example = "{\n" +
    	                          "  \"phoneNumber\": \"1234567890\",\n" +
    	                          "  \"phoneType\": \"mobile\",\n" +
    	                          "  \"isPrimary\": 1,\n" +
    	                          "  \"profile\": {\n" +
    	                          "    \"profileId\": 9\n" +
    	                          "  }\n" +
    	                          "}"
    	            )
    	        )
    	    ),
    	    responses = {
    	        @ApiResponse(responseCode = "200", description = "Ok"),
    	        @ApiResponse(responseCode = "400", description = "Error de acceso", content = {@Content()}),
    	        @ApiResponse(responseCode = "500", description = "Error de servidor", content = {@Content()})
    	    }
    	)
    	@PostMapping(value = "user/phone-add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    	public ResponseEntity<Phone> createPhone(@Valid @RequestBody Phone phone) {
    	    Phone newPhone = service.savePhone(phone);
    	    return new ResponseEntity<>(newPhone, HttpStatus.CREATED);
    	}

    @Operation(
			summary = "Actualizar numeros de telefonos ",
			description = "Permite actualizar los diferentes numeros de telefonos en la base de datos."
	)
    @ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Ok"),
			@ApiResponse(responseCode = "400",description = "Error de Acceso", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
    @PutMapping(value = "user/phone-update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Phone> updatePhone(@Valid @RequestParam Integer id, @RequestBody Phone phone) {
        Phone updatedPhone = service.updatePhone(phone, id);
        return new ResponseEntity<>(updatedPhone, HttpStatus.OK);
    }
}
