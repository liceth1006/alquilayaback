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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alquilaya.entity.OrderDetail;
import com.alquilaya.security.IJwtService;
import com.alquilaya.service.IOrderDetailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/order-details")
@Tag(name = "12 - Detalles de ordenes", description = "Operaciones relacionadas con los detalles de las ordenes")
public class OrderDetailController {

    @Autowired
    IOrderDetailService service;
    
    @Autowired
	private IJwtService jwtService;

    @Operation(
			summary = "Obtiene todos los detalles de las ordenes",
			description = "Obtiene todos los detalles de todas las ordenes que se encuentren registradas en la base de datos"
	)
    @ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Operacion exitosa"),
			@ApiResponse(responseCode = "403",description = "Acceso denegado", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
    @GetMapping(value="/user/orderdetail-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderDetail>> getAllOrderDetails() {
        List<OrderDetail> orderDetails = service.findAllOrderDetails();
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

    @Operation(
			summary = "Obtiene un detalle especificos de una orden",
			description = "Obtiene un detalle de una orden, buscandolo por su id"
	)
    @ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Operacion exitosa"),
			@ApiResponse(responseCode = "403",description = "Acceso denegado", content = {@Content()}),
			@ApiResponse(responseCode = "404",description = "No encontrado", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
    @GetMapping(value = "/admin/orderdetail-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDetail> getOrderDetailById(@RequestParam int id) {
        Optional<OrderDetail> orderDetail = service.findOrderDetailById(id);
        return orderDetail.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(
			summary = "Obtiene todos los detalles de una orden",
			description = "Obtiene todos los detalles de una orden, buscandolo por el id de su orden"
	)
    @ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Operacion exitosa"),
			@ApiResponse(responseCode = "403",description = "Acceso denegado", content = {@Content()}),
			@ApiResponse(responseCode = "404",description = "No encontrado", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
    @GetMapping(value = "/order/orderId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderDetail>> getOrderDetailsByOrderId(@RequestParam int orderId) {
        List<OrderDetail> orderDetails = service.findOrderDetailsByOrderId(orderId);
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

    @Operation(
			summary = "Crea un detalle",
			description = "Crea un detalle para una orden"
	)
    @ApiResponses(value = {
			@ApiResponse(responseCode = "201",description = "Recurso creado"),
			@ApiResponse(responseCode = "400",description = "Solicitud incorrecta", content = {@Content()}),
			@ApiResponse(responseCode = "403",description = "Acceso denegado", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
    @PostMapping(value="add", consumes = {"application/json", "application/json;charset=UTF-8"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDetail> createOrderDetail(@RequestBody OrderDetail orderDetail, @RequestParam Integer userID) {
        OrderDetail newOrderDetail = service.saveOrderDetail(orderDetail, userID);
        return new ResponseEntity<>(newOrderDetail, HttpStatus.CREATED);
    }

    @Operation(
			summary = "Actualiza un detalle",
			description = "Actualiza los datos del detalle de una orden"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Operacion Exitosa"),
			@ApiResponse(responseCode = "201",description = "Recurso creado", content = {@Content()}),
			@ApiResponse(responseCode = "400",description = "Solicitud incorrecta", content = {@Content()}),
			@ApiResponse(responseCode = "403",description = "Acceso denegado", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
    @PutMapping(value = "/user/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDetail> updateOrderDetail(@RequestParam Integer id, @RequestBody OrderDetail orderDetail) {
        OrderDetail updatedOrderDetail = service.updateOrderDetail(orderDetail, id);
        return new ResponseEntity<>(updatedOrderDetail, HttpStatus.OK);
    }
    
    @Operation(
			summary = "Obtiene todos los productos guardados en un carrito",
			description = "Obtiene todos los productos guardados en un carrito, buscandolos por el usuario de la orden"
	)
    @ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Operacion exitosa"),
			@ApiResponse(responseCode = "403",description = "Acceso denegado", content = {@Content()}),
			@ApiResponse(responseCode = "404",description = "No encontrado", content = {@Content()}),
			@ApiResponse(responseCode = "500",description = "Error de servidor", content = {@Content()})
	})
    @GetMapping(value = "/order/userId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderDetail>> getCart(@RequestHeader("Authorization") String authHeader) {
    	if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    	String token = authHeader.replace("Bearer ", "");
    	
    	int userId;
        try {
        	userId = jwtService.extractUserId(token, "access");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    	
        List<OrderDetail> orderDetails = service.cart(userId);
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }
}
