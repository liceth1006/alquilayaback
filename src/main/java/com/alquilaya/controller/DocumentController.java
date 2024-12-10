package com.alquilaya.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alquilaya.entity.Document;
import com.alquilaya.service.IDocumentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/documents")
@Tag(name = "16 - Documentos", description = "Operaciones relacionadas con los documentos")
@AllArgsConstructor
public class DocumentController {

    @Autowired
    IDocumentService service;

    @Operation(
			summary = "Obtiene todos los documentos",
			description = "Obtiene todos los documentos que se encuentran registradas en la base de datos"
	)
    @ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Operacion exitosa"),
			@ApiResponse(responseCode = "403",description = "Acceso denegado", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
    @GetMapping(value="/admin/document-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Document>> getAllDocuments() {
        List<Document> documents = service.findAllDocuments();
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    @Operation(
			summary = "Obtiene un documento especifico",
			description = "Obtiene un documento especifico, buscandolo por su id"
	)
    @ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Operacion exitosa"),
			@ApiResponse(responseCode = "403",description = "Acceso denegado", content = {@Content()}),
			@ApiResponse(responseCode = "404",description = "No encontrado", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
    @GetMapping(value = "/user/document-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Document> getDocumentById(@RequestParam Integer id) {
        Optional<Document> document = service.findDocumentById(id);
        return document.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(
			summary = "Agrega un documento",
			description = "Crea un documento en la base de datos"
	)
    @ApiResponses(value = {
			@ApiResponse(responseCode = "201",description = "Recurso creado"),
			@ApiResponse(responseCode = "400",description = "Solicitud incorrecta", content = {@Content()}),
			@ApiResponse(responseCode = "403",description = "Acceso denegado", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
    @PostMapping(value="/user/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Document> createDocument(@RequestBody Document document) {
        Document newDocument = service.saveDocument(document);
        return new ResponseEntity<>(newDocument, HttpStatus.CREATED);
    }
   
    @Operation(
			summary = "Actualiza un documento",
			description = "Actualiza los datos de un documento"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Operacion Exitosa"),
			@ApiResponse(responseCode = "201",description = "Recurso creado", content = {@Content()}),
			@ApiResponse(responseCode = "400",description = "Solicitud incorrecta", content = {@Content()}),
			@ApiResponse(responseCode = "403",description = "Acceso denegado", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
    @PutMapping(value = "/user/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Document> updateDocument(@RequestParam Integer id, @RequestBody Document document) {
        Document updatedDocument = service.updateDocument(document, id);
        return new ResponseEntity<>(updatedDocument, HttpStatus.OK);
    }
    
    @Autowired
    HttpServletRequest request;
    
    @PostMapping("/upload")
    public Map<String, String> uploadDocument(@RequestParam("file") MultipartFile multipartfile, @RequestParam Integer id) {
    	String path = service.store(multipartfile, id);
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
    
}
