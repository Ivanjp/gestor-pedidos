package com.liverpool.gestion_pedidos.service;

import com.liverpool.gestion_pedidos.dto.OrderDTO;
import com.liverpool.gestion_pedidos.dto.OrderQuantityDTO;

import java.util.List;

public interface IOrderService {

    List<OrderDTO> getOrders();
    OrderDTO getOrderById(Long orderId);
    List<OrderDTO> getOrdersByCustomer(Long customerId);
    OrderDTO createOrder(OrderDTO orderDTO);
    OrderDTO updateOrder(Long orderId, OrderQuantityDTO cantidad);
    void deleteOrder(Long orderId);
}
