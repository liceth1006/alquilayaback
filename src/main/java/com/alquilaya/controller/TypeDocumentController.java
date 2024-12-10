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

import com.alquilaya.entity.TypeDocument;
import com.alquilaya.service.ITypeDocumentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/")
@Tag(name = "3 - Tipo de documentos", description = "Operaciones relacionadas con el tipo de documentos agregado a la base de datos.")
public class TypeDocumentController {

    @Autowired
    ITypeDocumentService service;
    
    @Operation(
			summary = "Consultar el tipo de documento",
			description = "Accede al registro completo de todos los tipos de documentos disponibles en la base de datos."
	)
    @ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Ok"),
			@ApiResponse(responseCode = "400",description = "Error de Acceso", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
    @GetMapping(value="public/typedocuments-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TypeDocument>> getAllTypeDocuments() {
        List<TypeDocument> typeDocuments = service.findAllTypeDocuments();
        return new ResponseEntity<>(typeDocuments, HttpStatus.OK);
    }
    
    @Operation(
			summary = "Consultar el tipo de documento por ID",
			description = "Accede al registro unico de tipo de documento en la base de datos por ID."
	)
    @ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Ok"),
			@ApiResponse(responseCode = "400",description = "Error de Acceso", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
    @GetMapping(value = "public/typedocuments-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TypeDocument> getTypeDocumentById(@RequestParam Integer id) {
        Optional<TypeDocument> typeDocument = service.findTypeDocumentById(id);
        return typeDocument.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @Operation(
			summary = "Agregar tipo de documento",
			description = "permite agregar un tipo de documento en la base de datos."
	)
    @ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Ok"),
			@ApiResponse(responseCode = "400",description = "Error de Acceso", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
    @PostMapping(value="admin/typedocuments-add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TypeDocument> createTypeDocument(@Valid @RequestBody TypeDocument typeDocument) {
        TypeDocument newTypeDocument = service.saveTypeDocument(typeDocument);
        return new ResponseEntity<>(newTypeDocument, HttpStatus.CREATED);
    }
    
    @Operation(
			summary = "Actualizar tipo de documento",
			description = "Permite actualizar el tipo del documento de la base de datos."
	)
    @ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Ok"),
			@ApiResponse(responseCode = "400",description = "Error de Acceso", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
    @PutMapping(value = "admin/typedocuments-update",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TypeDocument> updateTypeDocument(@Valid @RequestParam Integer id, @RequestBody TypeDocument typeDocument) {
        TypeDocument updatedTypeDocument = service.updateTypeDocument(typeDocument, id);
        return new ResponseEntity<>(updatedTypeDocument, HttpStatus.OK);
    }
}
