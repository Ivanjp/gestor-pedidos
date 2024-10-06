package com.liverpool.gestion_pedidos.controller;

import com.liverpool.gestion_pedidos.dto.CustomerDTO;
import com.liverpool.gestion_pedidos.exception.ApiException;
import com.liverpool.gestion_pedidos.service.ICustomerService;
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
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    ICustomerService service;

    @Operation(summary = "Obtener los clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clientes encontrados"),
            @ApiResponse(responseCode = "404", description = "Clientes no encontrados"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getCustomers() {
        try{
            return new ResponseEntity<>(service.getCustomers(), HttpStatus.OK);
        }catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error");
        }
    }

    @Operation(summary = "Obtener un cliente mediante su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable("id") Long id) {
        try{
            return new ResponseEntity<>(service.getCustomerById(id), HttpStatus.OK);
        }catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error");
        }
    }

    @Operation(summary = "Crear un cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creado"),
            @ApiResponse(responseCode = "404", description = "Cliente no creado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO customer) {
        try{
            return new ResponseEntity<>(service.createCustomer(customer), HttpStatus.CREATED);
        }catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error");
        }
    }

    @Operation(summary = "Actualizar datos de un cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Datos del cliente actualizados"),
            @ApiResponse(responseCode = "404", description = "Datos del cliente no actualizados"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable("id") Long id, @Valid @RequestBody CustomerDTO customer) {
        try{
            return new ResponseEntity<>(service.updateCustomer(id,customer), HttpStatus.OK);
        }catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error");
        }
    }

    @Operation(summary = "Eliminar un cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente Eliminado"),
            @ApiResponse(responseCode = "404", description = "Cliente no eliminado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("id") Long id) {
        try{
            service.deleteCustomer(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error");
        }

    }

}
