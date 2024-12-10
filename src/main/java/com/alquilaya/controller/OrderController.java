package com.alquilaya.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alquilaya.entity.Order;
import com.alquilaya.service.IOrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/")
@Tag(name = "13 - Ordenes", description = "Operaciones relacionadas con las ordenes")
public class OrderController {

    @Autowired
    IOrderService service;
    
    @Operation(
			summary = "Obtiene todas las ordenes",
			description = "Obtiene todas las ordenes que se encuentren registradas en la base de datos"
	)
    @ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Operacion exitosa"),
			@ApiResponse(responseCode = "403",description = "Acceso denegado", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
    @GetMapping(value="admin/order-all",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = service.findAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @Operation(
			summary = "Obtiene una orden especifica",
			description = "Obtiene una orden esoecifica, buscandola por su id"
	)
    @ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Operacion exitosa"),
			@ApiResponse(responseCode = "403",description = "Acceso denegado", content = {@Content()}),
			@ApiResponse(responseCode = "404",description = "No encontrado", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
    @GetMapping(value = "order-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> getOrderById(@RequestParam Integer id) {
        Optional<Order> order = service.findOrderById(id);
        return order.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(
			summary = "Obtiene las ordenes de un usuario",
			description = "Obtiene todas las ordenes de un usuario, buscandolas por el id de este"
	)
    @ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Operacion exitosa"),
			@ApiResponse(responseCode = "403",description = "Acceso denegado", content = {@Content()}),
			@ApiResponse(responseCode = "404",description = "No encontrado", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
    @GetMapping(value = "admin/order-userid", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order>> getOrdersByUserId(@RequestParam Integer userId) {
        List<Order> orders = service.findOrdersByUserId(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @Operation(
			summary = "Actualiza una orden",
			description = "Actualiza los datos de una orden"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Operacion Exitosa"),
			@ApiResponse(responseCode = "201",description = "Recurso creado", content = {@Content()}),
			@ApiResponse(responseCode = "400",description = "Solicitud incorrecta", content = {@Content()}),
			@ApiResponse(responseCode = "403",description = "Acceso denegado", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
    @PutMapping(value = "admin/update-orders",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> updateOrder(@RequestParam Integer id, @RequestBody Order order) {
        Order updatedOrder = service.updateOrder(order, id);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }
    
    @Operation(
			summary = "Actualiza una orden",
			description = "Actualiza los datos de una orden"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Operacion Exitosa"),
			@ApiResponse(responseCode = "201",description = "Recurso creado", content = {@Content()}),
			@ApiResponse(responseCode = "400",description = "Solicitud incorrecta", content = {@Content()}),
			@ApiResponse(responseCode = "403",description = "Acceso denegado", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
    @PutMapping(value = "complete-order",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> completeOrder(@RequestParam Integer id, @RequestBody Order order) {
        Order updatedOrder = service.completeOrder(order, id);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }
}
