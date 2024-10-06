package com.liverpool.gestion_pedidos.service;

import com.liverpool.gestion_pedidos.dto.OrderDTO;
import com.liverpool.gestion_pedidos.dto.OrderQuantityDTO;

import java.util.List;

public interface IOrderService {

    List<OrderDTO> getOrders();
    OrderDTO getOrderById(Long id);
    List<OrderDTO> getOrdersByCustomer(Long Id);
    OrderDTO createOrder(OrderDTO order);
    OrderDTO updateOrder(Long id, OrderQuantityDTO cantidad);
    void deleteOrder(Long id);
}
