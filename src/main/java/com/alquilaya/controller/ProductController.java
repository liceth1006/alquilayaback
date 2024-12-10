package com.alquilaya.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alquilaya.dto.FavoriteProductDTO;
import com.alquilaya.dto.ProductProjectionDTO;
import com.alquilaya.entity.Product;
import com.alquilaya.security.IJwtService;
import com.alquilaya.service.IProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/v1/")
@Tag(name = "7 - Productos", description = "Operaciones relacionadas con productos.")
public class ProductController {

	@Autowired
	IProductService service;


    @Autowired
	private IJwtService jwtService;
	
    
    @GetMapping(value = "customer/product-user", produces = "application/json")
	 public ResponseEntity<List<ProductProjectionDTO>> getProduct(@RequestHeader("Authorization") String authHeader) {
         // Verificar si el encabezado Authorization está presente
         if (authHeader == null || !authHeader.startsWith("Bearer ")) {
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
         }

         // Extraer el token del encabezado (quitar el "Bearer ")
         String token = authHeader.replace("Bearer ", "");

         int userId;
         try {
             // Extraer el userId del token
             userId = jwtService.extractUserId(token, "access");
            
         } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
         }

         // Obtener los productos favoritos del usuario
         List<ProductProjectionDTO> products = service.findProductsByUserId(userId);

         // Si no hay favoritos, retornamos 404
         if (products.isEmpty()) {
             return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
         }

         // Retornar los favoritos con estado 200
         return new ResponseEntity<>(products, HttpStatus.OK);
     }
 

	
	
	
	@Operation(summary = "Obtener todos los productos", description = "Este endpoint retorna una lista de productos  con información relevante, como nombre, precio, imagen, etc. "
			+ "Se puede utilizar para mostrar productos en una vista tipo card en el frontend.", responses = {
					@ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductProjectionDTO.class))),
					@ApiResponse(responseCode = "404", description = "No se encontraron productos.", content = @Content(mediaType = "application/json")),
					@ApiResponse(responseCode = "500", description = "Error interno del servidor.", content = @Content(mediaType = "application/json")) })
	@GetMapping(value = "public/all-products", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductProjectionDTO>> findAllProducts() {
		List<ProductProjectionDTO> products = service.findAllProjectedProducts();
		if (products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@Operation(summary = "Obtener detalles de un producto por su ID", description = "Recupera los detalles de un producto a partir de su ID proporcionado como un parámetro de consulta. Devuelve 404 si el producto no se encuentra.", responses = {
			@ApiResponse(responseCode = "200", description = "Producto encontrado", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "400", description = "ID no válido proporcionado"),
			@ApiResponse(responseCode = "404", description = "Producto no encontrado") })
	@GetMapping(value = "/public/products-id", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductProjectionDTO> findProductById(
			@Parameter(description = "ID del producto que se desea recuperar", example = "1", required = true) @RequestParam(name = "id") Integer id) {

		if (id == null || id <= 0) {
			return ResponseEntity.badRequest().build();
		}
		return service.findProjectedProductsById(id).map(product -> ResponseEntity.ok(product))
				.orElse(ResponseEntity.notFound().build());
	}

	@Operation(summary = "Filtrar productos", description = "Permite filtrar productos por nombre de categoría, nombre del proveedor y estado del producto.", responses = {
			@ApiResponse(responseCode = "200", description = "Productos filtrados encontrados", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "No se encontraron productos con los filtros aplicados") })
	@GetMapping(value = "/public/filter", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductProjectionDTO>> filterProducts(
			@RequestParam(name = "categoryName", required = false) String categoryName,
			@RequestParam(name = "supplierName", required = false) String supplierName,
			@RequestParam(name = "productCondition", required = false) String productCondition) {

		List<ProductProjectionDTO> products = service.filterProducts(categoryName, supplierName, productCondition);

		if (products.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(products);
	}

	@Operation(summary = "Obtener productos por categoría", description = "Filtra productos por el ID de categoría. Retorna los productos que pertenecen a la categoría proporcionada. Productos relacionados", responses = {
			@ApiResponse(responseCode = "200", description = "Productos filtrados encontrados", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "No se encontraron productos con el ID de categoría proporcionado") })
	@GetMapping(value = "/public/filter-category", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductProjectionDTO>> filterProductsByCategory(
			@RequestParam(name = "categoryId", required = true) Integer categoryId) {

		List<ProductProjectionDTO> products = service.findProjectedProductsByCategoryId(categoryId);

		if (products.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(products);
	}
	
	@Operation(
		    summary = "Crear un nuevo producto",
		    description = "Este endpoint permite crear un nuevo producto en la base de datos. El producto debe ser enviado en formato JSON y contiene detalles como el nombre, precio, categoría, proveedor, estado y condición del producto.",
		    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
		        description = "Objeto que representa un nuevo producto, incluyendo su nombre, descripción, precio, disponibilidad, condición, categoría y proveedor.",
		        content = @Content(
		            mediaType = "application/json",
		            schema = @Schema(
		                example = "{\n" +
		                          "  \"name\": \"Producto Ejemplo\",\n" +
		                          "  \"description\": \"Descripción válida\",\n" +
		                          "  \"price\": 100.50,\n" +
		                          "  \"isAvailable\": 1,\n" +
		                          "  \"productCondition\": \"new\",\n" +
		                          "  \"category\": {\n" +
		                          "    \"categoryId\": 1\n" +
		                          "  },\n" +
		                          "  \"supplier\": {\n" +
		                          "    \"userId\": 2\n" +
		                          "  }\n" +
		                          "}"
		            )
		        )
		    ),
		    responses = {
		        @ApiResponse(responseCode = "201", description = "Producto creado exitosamente."),
		        @ApiResponse(responseCode = "400", description = "Solicitud inválida.")
		    }
		)
		@PostMapping(value = "user/add-products", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
		    Product productName = service.createProduct(product);
		    
		    return new ResponseEntity<Product>(productName, HttpStatus.CREATED);
		}

	
	@Operation(
		    summary = "Actualizar un producto existente",
		    description = "Este endpoint permite actualizar un producto existente en la base de datos. El ID del producto debe ser pasado como un parámetro en la URL, mientras que los detalles del producto (como nombre, precio, etc.) se envían en el cuerpo de la solicitud.",
		    parameters = {
		        @Parameter(
		            name = "id", 
		            description = "ID del producto a actualizar", 
		            required = true,
		            example = "1"
		        )
		    },
		    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
		        description = "Detalles del producto que se desea actualizar", 
		        content = @Content(
		            mediaType = "application/json", 
		            schema = @Schema(
		                example = "{\n" +
		                          "  \"name\": \"Producto Ejemplo\",\n" +
		                          "  \"description\": \"Descripción válida\",\n" +
		                          "  \"price\": 100.50,\n" +
		                          "  \"isAvailable\": 1,\n" +
		                          "  \"productCondition\": \"new\",\n" +
		                          "  \"category\": {\n" +
		                          "    \"categoryId\": 1\n" +
		                          "  },\n" +
		                          "  \"supplier\": {\n" +
		                          "    \"userId\": 2\n" +
		                          "  }\n" +
		                          "}"
		            )
		        )
		    ),
		    responses = {
		        @ApiResponse(
		            responseCode = "200", 
		            description = "Producto actualizado exitosamente", 
		            content = @Content(
		                mediaType = "application/json", 
		                schema = @Schema(implementation = Product.class)
		            )
		        ),
		        @ApiResponse(
		            responseCode = "400", 
		            description = "ID no válido o solicitud incorrecta (por ejemplo, campos faltantes)"
		        ),
		        @ApiResponse(
		            responseCode = "404", 
		            description = "Producto no encontrado con el ID proporcionado"
		        ),
		        @ApiResponse(
		            responseCode = "500", 
		            description = "Error interno del servidor"
		        )
		    }
		)
		@PutMapping(value = "/user/update-product", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Product> updateProduct(
		    @Parameter(description = "ID del producto a actualizar", example = "1", required = true) @RequestParam(name = "id") Integer id, 
		    @RequestBody Product product) {
		    Product updatedProduct = service.updateProduct(product, id);String message = "Se actualizo con éxito el producto: " + updatedProduct.getName();
		    return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
		}

	
	@GetMapping("/public/search")
	public ResponseEntity<List<ProductProjectionDTO>> searchProducts(@RequestParam String name, @RequestParam String city) {
		List<ProductProjectionDTO> products = service.findProductsByNameAndCity(name, city);
		if (products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	
	
	/*

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = service.findAllProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
		Optional<Product> product = service.findProductById(id);
		return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping(value = "/category/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> getProductsByCategoryId(@PathVariable Integer categoryId) {
		List<Product> products = service.findProductsByCategoryId(categoryId);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}



	
	
	*/

}
