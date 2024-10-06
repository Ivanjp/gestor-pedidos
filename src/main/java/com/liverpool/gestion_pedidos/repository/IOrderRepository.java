package com.liverpool.gestion_pedidos.repository;

import com.liverpool.gestion_pedidos.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<OrderModel,Long> {
    List<OrderModel> findByCustomerId(Long customerId);
}
