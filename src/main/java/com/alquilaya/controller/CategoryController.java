package com.alquilaya.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alquilaya.entity.Category;
import com.alquilaya.service.ICategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;



@RestController
@RequestMapping("/api/v1/")
@Tag(name = "2 - Categoria Productos", description = "Operaciones relacionadas con las categorias del producto.")
public class CategoryController {

	@Autowired
	ICategoryService service;
	
	@Operation(
			summary = "Obtener todas las categorias del producto",
			description = "Este endpoint permite obtener todas las categorias de productos."
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Ok"),
			@ApiResponse(responseCode = "400",description = "Error de Acceso", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
	@GetMapping(value="public/category-all",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Category>> getAllCategories() {
		List<Category> categories = service.findAllCategories();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
	
	
	@Operation(
			summary = "Obtener categorias por ID",
			description = "Obtiene las diferentes categorias de productos registrados  en la base de datos por ID."
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Ok"),
			@ApiResponse(responseCode = "400",description = "Error de Acceso", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
	@GetMapping(value = "public/category-id", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Category> getCategoryById(@RequestParam Integer id) {
		Optional<Category> category = service.findCategoryById(id);
		return category.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@Operation(
		    summary = "registra las  categorías de los productos",
		    description = "Permite actualizar las categorías de productos en la base de datos.",
		    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
		        description = "Objeto que representa una categoría de producto, incluyendo su nombre, descripción y otros detalles.",
		        content = @Content(
		            mediaType = "application/json",
		            schema = @Schema(
		            		example = "{\n" +
		            		          "  \"name\": \"Categoria Ejemplo\",\n" +
		            		          "  \"description\": \"Descripción válida\"\n" +
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
	@PostMapping(value="admin/category-add",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Category> createCategory(@RequestBody Category category) {
		Category newCategory = service.saveCategory(category);
		return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
	}
	
	
	@Operation(
		    summary = "Actualizar categorías de productos",
		    description = "Permite actualizar las categorías de productos en la base de datos.",
		    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
		        description = "Objeto que representa una categoría de producto, incluyendo su nombre, descripción y otros detalles.",
		        content = @Content(
		            mediaType = "application/json",
		            schema = @Schema(
		            		example = "{\n" +
		            		          "  \"name\": \"Categoria Ejemplo\",\n" +
		            		          "  \"description\": \"Descripción válida\"\n" +
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
	@PutMapping(value = "admin/category-update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Category> updateCategory(@RequestParam Integer id, @RequestBody Category category) {
		Category updatedCategory = service.updateCategory(category, id);
		return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
	}
	
	
	 @Operation(summary = "Actualiza el estado de un tipo de curso y ya", description = "Permite cambiar el estado de un tipo de curso a activo o inactivo.")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "200",description = "Ok"),
				@ApiResponse(responseCode = "400",description = "Error de Acceso", content = {@Content()}),
				@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
		})
	 
	 @DeleteMapping(value = "admin/category-delete", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Category> deleteCategory(@RequestParam Integer id) {
		 Category updatedCourse = service.deleteCategory(id);
	    if (updatedCourse != null) {
	        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
	    }
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
