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

import com.alquilaya.entity.Profile;
import com.alquilaya.service.IProfileService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/profiles")
@Tag(name = "Johel - Perfil", description = "Operaciones relacionadas con los perfiles")
public class ProfileController {

    @Autowired
    IProfileService service;

    @Operation(
			summary = "Obtiene todos los perfiles",
			description = "Obtiene todas los perfiles que se encuentren registrados en la base de datos"
	)
    @ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Operacion exitosa"),
			@ApiResponse(responseCode = "403",description = "Acceso denegado", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
    @GetMapping(value="public/profile-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Profile>> getAllProfiles() {
        List<Profile> profiles = service.findAllProfiles();
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

    @Operation(
			summary = "Obtiene un perfil especifico",
			description = "Obtiene un perfil especifico, buscandola por su id"
	)
    @ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Operacion exitosa"),
			@ApiResponse(responseCode = "403",description = "Acceso denegado", content = {@Content()}),
			@ApiResponse(responseCode = "404",description = "No encontrado", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
    @GetMapping(value = "public/profile-id",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Profile> getProfileById(@RequestParam Integer id) {
        Optional<Profile> profile = service.findProfileById(id);
        return profile.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(
			summary = "Obtiene el perfil de un usuario",
			description = "Obtiene todos los datos del perfil de un usuario, buscandolas por el id de este"
	)
    @ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Operacion exitosa"),
			@ApiResponse(responseCode = "403",description = "Acceso denegado", content = {@Content()}),
			@ApiResponse(responseCode = "404",description = "No encontrado", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
    @GetMapping(value = "/user/userId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Profile> getProfileByUserId(@RequestParam Integer userId) {
        Optional<Profile> profile = service.findProfileByUserId(userId);
        return profile.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(
			summary = "Genera una nuevo perfil",
			description = "Crea una nuevo perfil y lo guarda en la base de datos"
	)
    @ApiResponses(value = {
			@ApiResponse(responseCode = "201",description = "Recurso creado"),
			@ApiResponse(responseCode = "400",description = "Solicitud incorrecta", content = {@Content()}),
			@ApiResponse(responseCode = "403",description = "Acceso denegado", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
    @PostMapping(value="/user/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile) {
        Profile newProfile = service.saveProfile(profile);
        return new ResponseEntity<>(newProfile, HttpStatus.CREATED);
    }

   
}
