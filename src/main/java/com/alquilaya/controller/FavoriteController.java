package com.alquilaya.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alquilaya.dto.FavoriteProductDTO;
import com.alquilaya.dto.ProductProjectionDTO;
import com.alquilaya.entity.Favorite;
import com.alquilaya.security.IJwtService;
import com.alquilaya.service.IFavoriteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/")
@Tag(name = "4 - Favoritos", description = "Operaciones relacionadas con los favoritos de los usuarios.")
public class FavoriteController {

    @Autowired
    IFavoriteService service;
    
    @Autowired
	private IJwtService jwtService;
    
    @Operation(
			summary = "Consultar lista de favoritos ",
			description = "Accede al registro completo de todos los productos favoritos de la base de datos."
	)
    @ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Ok"),
			@ApiResponse(responseCode = "400",description = "Error de Acceso", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
    @GetMapping(value="admin/favorite-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FavoriteProductDTO>> getAllFavorites() {
        List<FavoriteProductDTO> favorites = service.findAllFavoriteProducts();
        return new ResponseEntity<>(favorites, HttpStatus.OK);
    }
   
    @Operation(
			summary = "Agregar favoritos",
			description = "Agregar diferentes productos favoritos a la base de datos."
	)
    @ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Ok"),
			@ApiResponse(responseCode = "400",description = "Error de Acceso", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
    @PostMapping(value="user/favorite-add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Favorite> createFavorite(@Valid @RequestBody Favorite favorite) {
        Favorite newFavorite = service.saveFavorite(favorite);
        return new ResponseEntity<>(newFavorite, HttpStatus.CREATED);
    }
    
    @Operation(
			summary = "Eliminar de favoritos",
			description = "Permite eliminar los diferentes productos que ya no se deseen en favoritos."
	)
    @ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Ok"),
			@ApiResponse(responseCode = "400",description = "Error de Acceso", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
    @DeleteMapping(value = "/customer/favorite-delete")
    public ResponseEntity<Void> deleteFavorite(@RequestParam Integer id) {
        service.deleteFavorite(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    
    @Operation(
            summary = "Obtener todos los favoritos de un usuario",
            description = "Este endpoint permite obtener todos los productos favoritos de un usuario, utilizando un token JWT válido."
        )
        @GetMapping(value = "customer/favorite-user", produces = "application/json")
        public ResponseEntity<List<FavoriteProductDTO>> getFavorites(@RequestHeader("Authorization") String authHeader) {
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
            List<FavoriteProductDTO> favoriteProducts = service.findFavoriteProductsByUserId(userId);

            // Si no hay favoritos, retornamos 404
            if (favoriteProducts.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            // Retornar los favoritos con estado 200
            return new ResponseEntity<>(favoriteProducts, HttpStatus.OK);
        }
    

    @Operation(
    	    summary = "Obtener todos los favoritos de varios usuarios",
    	    description = "Este endpoint permite obtener todos los productos favoritos de varios usuarios, pasando sus userIds como parámetros."
    	)
    	@GetMapping(value = "admin/favorite-id", produces = "application/json")
    	public ResponseEntity<List<FavoriteProductDTO>> getFavoritesByUserIds(
    	    @RequestParam int userId) {


    	 // Obtener los productos favoritos del usuario
            List<FavoriteProductDTO> favoriteProducts = service.findFavoriteProductsByUserId(userId);
    	    // Si no hay favoritos, retornamos 404
    	    if (favoriteProducts.isEmpty()) {
    	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    	    }

    	    // Retornar los favoritos con estado 200
    	    return new ResponseEntity<>(favoriteProducts, HttpStatus.OK);
    	}

}
