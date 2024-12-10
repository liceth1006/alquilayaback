package com.alquilaya.controller;


import java.util.List;
import java.util.Map;


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

import com.alquilaya.dto.UserProfileDTO;
import com.alquilaya.dto.UserRegistrationDTO;
import com.alquilaya.entity.Profile;
import com.alquilaya.security.FirebaseAuthService;
import com.alquilaya.security.IJwtService;
import com.alquilaya.service.IProfileService;
import com.alquilaya.service.IUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/v1/")
@Tag(name = "1 - AuthController", description = "Operaciones relacionadas con la autenticación de los usuarios.")
public class AuthController {

	@Autowired
	IUserService service;

	@Autowired
	private IJwtService jwtService;
	
	 @Autowired
	 IProfileService serviceProfile;
	 
	 @Autowired
	    private FirebaseAuthService firebaseAuthService;



	    @PostMapping("public/firebase-login")
	    public ResponseEntity<?>  login(@RequestParam String token) {
	        try {
	        	Map<String, String>  tokens = firebaseAuthService.verifyTokenAndSaveUser(token);
	        	return ResponseEntity.ok(tokens);
	        } catch (Exception e) {
	        	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error de autenticación: " + e.getMessage());
	        }
	    }
	    
	@Operation(summary = "Refrescar el accessToken", description = "Este endpoint permite a un usuario obtener un nuevo accessToken utilizando un refreshToken válido. "
			+ "El refreshToken debe ser enviado en el cuerpo de la solicitud, y si es válido, se devolverá un nuevo accessToken que puede ser usado para autenticar futuras solicitudes.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Refresco de token exitoso", content = @Content(mediaType = "application/json", schema = @Schema(type = "object", example = "{\"accessToken\":\"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.Lh2f4G_HtZ...\"}"))),
			@ApiResponse(responseCode = "400", description = "El refreshToken es requerido o está mal formado", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "Error: El refreshToken es requerido"))),
			@ApiResponse(responseCode = "401", description = "El refreshToken es inválido, expirado o ha sido usado", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "Error de autenticación: refreshToken inválido"))),
			@ApiResponse(responseCode = "500", description = "Error inesperado en el servidor", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "Error inesperado en el servidor"))) })
	@PostMapping(value = "public/refresh-token", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> request) {
		String refreshToken = request.get("refreshToken");

		// Validar si el refreshToken fue enviado
		if (refreshToken == null || refreshToken.isEmpty()) {
			return ResponseEntity.badRequest().body("El refreshToken es requerido");
		}

		try {
			// Intentar refrescar el accessToken usando el refreshToken
			String newAccessToken = service.refreshAccessToken(refreshToken);

			// Respuesta exitosa con el nuevo accessToken
			return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
		} catch (RuntimeException e) {
			// Manejo de errores: token no válido, expirado, o usado
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(Map.of("error", "Error de autenticación: " + e.getMessage()));
		} catch (Exception e) {
			// Manejo de errores inesperados
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("error", "Error inesperado en el servidor"));
		}
	}


	
	@Operation(summary = "Obtener detalles del usuario autenticado", description = "Este endpoint permite a un usuario autenticado obtener los detalles de su cuenta. "
			+ "El usuario debe proporcionar un token de autenticación válido en el encabezado de la solicitud para obtener los datos personales asociados a su cuenta.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Detalles del usuario obtenidos exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserRegistrationDTO.class))),
			@ApiResponse(responseCode = "401", description = "Token de autenticación no válido o expirado", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "Error: Token de autenticación no válido o expirado."))),
			@ApiResponse(responseCode = "400", description = "Solicitud incorrecta, falta el encabezado de autorización", content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "Error: El encabezado Authorization es requerido."))) })
	@GetMapping(value = "user/details-user", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserProfileDTO> getDetailsUser(@RequestHeader("Authorization") String authHeader) {
		// Extraer el token del encabezado
		String token = authHeader.replace("Bearer ", "");

		// Extraer el userId del token
		int userId = jwtService.extractUserId(token, "access");

		// Obtener los detalles del usuario
		UserProfileDTO userDetails = service.loadUserDetails(userId);

		return ResponseEntity.ok(userDetails);
	}
	
	@Operation(
		    summary = "Obtener todos los usuarios con sus perfiles",
		    description = "Este endpoint permite al administrador obtener una lista de todos los usuarios junto con la información de sus perfiles."
		)
		@ApiResponses(value = {
		    @ApiResponse(
		        responseCode = "200",
		        description = "Lista de usuarios obtenida exitosamente",
		        content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserProfileDTO.class))
		    ),
		    @ApiResponse(
		        responseCode = "401",
		        description = "Token de autenticación no válido o expirado",
		        content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "Error: Token de autenticación no válido o expirado."))
		    ),
		    @ApiResponse(
		        responseCode = "403",
		        description = "Acceso denegado, se requiere rol de administrador",
		        content = @Content(mediaType = "application/json", schema = @Schema(type = "string", example = "Error: No tiene permisos para acceder a este recurso."))
		    )
		})
		@GetMapping(value = "admin/user-all", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<UserProfileDTO>> getDetailsUserAll() {
		   
		    List<UserProfileDTO> userDetails = service.findAllUsers();

		    return ResponseEntity.ok(userDetails);
		}

	@Operation(
		    summary = "Actualiza un perfil",
		    description = "Actualiza los datos de un perfil. El usuario debe proporcionar un token de autenticación válido en el encabezado de la solicitud para realizar la actualización."
		)
		@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Operación exitosa"),
		    @ApiResponse(responseCode = "201", description = "Recurso creado"),
		    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
		    @ApiResponse(responseCode = "403", description = "Acceso denegado"),
		    @ApiResponse(responseCode = "500", description = "Error de servidor")
		})
	@PutMapping("user/update-profile")
    public ResponseEntity<Boolean> updateProfile(@RequestHeader("Authorization") String authHeader, @RequestBody Profile profile) {
        // Verificar si el encabezado Authorization está presente
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);  // Si no se incluye el token, retornamos 401
        }

        // Extraer el token del encabezado (quitar el "Bearer ")
        String token = authHeader.replace("Bearer ", "");

        // Extraer el userId del token
        int userId;
        try {
            userId = jwtService.extractUserId(token, "access");  // Este método debe manejar posibles errores
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);  // Si el token no es válido, retornamos 401
        }

        // Llamar al servicio para actualizar el perfil
        boolean isUpdated = serviceProfile.updateProfile(profile, userId);

        // Si no se pudo actualizar el perfil, retornar una respuesta con estado 404
        if (!isUpdated) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);  // Perfil no encontrado o no actualizado
        }

        // Si se actualiza correctamente, retornar true con estado 200
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}

