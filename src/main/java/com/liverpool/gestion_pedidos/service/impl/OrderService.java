package com.liverpool.gestion_pedidos.service.impl;

import com.liverpool.gestion_pedidos.dto.OrderDTO;
import com.liverpool.gestion_pedidos.dto.OrderQuantityDTO;
import com.liverpool.gestion_pedidos.exception.ApiException;
import com.liverpool.gestion_pedidos.model.CustomerModel;
import com.liverpool.gestion_pedidos.model.OrderModel;
import com.liverpool.gestion_pedidos.repository.ICustomerRepository;
import com.liverpool.gestion_pedidos.repository.IOrderRepository;
import com.liverpool.gestion_pedidos.service.IOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {

    @Autowired
    IOrderRepository repository;

    @Autowired
    ICustomerRepository repositoryC;

    @Override
    public List<OrderDTO> getOrders() {
        List<OrderModel> orderModels = repository.findAll();

        return orderModels.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(Long orderId) {
        OrderModel orderModel = repository.findById(orderId).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Order not found"));
        return convertToDTO(orderModel);
    }

    @Override
    public List<OrderDTO> getOrdersByCustomer(Long customerId) {

        if(!repositoryC.existsById(customerId)){
            throw new ApiException(HttpStatus.NOT_FOUND, "Customer not found");
        }

        List<OrderModel> orderModels = repository.findByCustomerId(customerId);

        return orderModels.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public OrderDTO createOrder(@Valid OrderDTO orderDTO) {

        CustomerModel customer = repositoryC.findById(orderDTO.getCustomerId())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Customer not found"));

        OrderModel orderModel = convertToModel(orderDTO,customer);

        OrderModel newOrder = repository.save(orderModel);

        return convertToDTO(newOrder);
    }

    @Override
    public OrderDTO updateOrder(Long orderId, @Valid OrderQuantityDTO cantidad) {
        OrderModel orderModel = repository.findById(orderId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Order not found "));

        orderModel.setCantidad(cantidad.getCantidad());

        OrderModel updatedOrder = repository.save(orderModel);

        return convertToDTO(updatedOrder);
    }

    @Override
    public void deleteOrder(Long orderId) {
        if (!repository.existsById(orderId)) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Order not found");
        }
        repository.deleteById(orderId);
    }

    private OrderDTO convertToDTO(OrderModel model){
        OrderDTO dto = new OrderDTO();

        dto.setId(model.getId());
        dto.setCodigoProducto(model.getCodigoProducto());
        dto.setCantidad(model.getCantidad());
        dto.setPrecio(model.getPrecio());
        dto.setCustomerId(model.getCustomer().getId());

        return dto;

    }

    private OrderModel convertToModel(OrderDTO dto, CustomerModel customer){
        OrderModel model = new OrderModel();

        model.setCodigoProducto(dto.getCodigoProducto());
        model.setCantidad(dto.getCantidad());
        model.setPrecio(dto.getPrecio());
        model.setCustomer(customer);

        return model;

    }
}
