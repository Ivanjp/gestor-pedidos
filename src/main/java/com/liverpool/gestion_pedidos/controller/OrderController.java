package com.liverpool.gestion_pedidos.controller;

import com.liverpool.gestion_pedidos.dto.OrderDTO;
import com.liverpool.gestion_pedidos.dto.OrderQuantityDTO;
import com.liverpool.gestion_pedidos.exception.ApiException;
import com.liverpool.gestion_pedidos.service.IOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    IOrderService service;

    @Operation(summary = "Obtener los pedidos existentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedidos encontrados"),
            @ApiResponse(responseCode = "404", description = "Pedidos no encontrados"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getOrders() {
        try{
            return new ResponseEntity<>(service.getOrders(), HttpStatus.OK);
        }catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error");
        }

    }

    @Operation(summary = "Obtener pedido por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido encontrado"),
            @ApiResponse(responseCode = "404", description = "Pedido no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable("id") Long id) {
        try{
            return new ResponseEntity<>(service.getOrderById(id), HttpStatus.OK);
        }catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error");
        }

    }

    @Operation(summary = "Obtener los pedidos de un cliente mediante su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedidos encontrados"),
            @ApiResponse(responseCode = "404", description = "Pedidos no encontrados"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("customer/{id}")
    public ResponseEntity<List<OrderDTO>> getOrdersByCustomer(@PathVariable("id") Long id) {
        try{
            return new ResponseEntity<>(service.getOrdersByCustomer(id), HttpStatus.OK);
        }catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error");
        }
    }

    @Operation(summary = "Crear un pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido creado"),
            @ApiResponse(responseCode = "404", description = "Pedido no creado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody @Valid OrderDTO order) {
        try{
            return new ResponseEntity<>(service.createOrder(order), HttpStatus.CREATED);
        }catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error");
        }
    }

    @Operation(summary = "Actualizar la cantidad de un producto de un pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cantidad actualizada"),
            @ApiResponse(responseCode = "404", description = "Cantidad no actualizada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/{id}/quantity")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @Valid @RequestBody OrderQuantityDTO cantidad) {
        try{
            return new ResponseEntity<>(service.updateOrder(id,cantidad), HttpStatus.OK);
        }catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error");
        }
    }

    @Operation(summary = "Eliminar un pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pedido eliminad"),
            @ApiResponse(responseCode = "404", description = "Pedido no eliminado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteOrder(@PathVariable("id") Long id) {
        try{
            service.deleteOrder(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error");
        }

    }
}
