package com.liverpool.gestion_pedidos.repository;

import com.liverpool.gestion_pedidos.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<CustomerModel,Long> {

}
