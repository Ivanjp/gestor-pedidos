package com.liverpool.gestion_pedidos.service;

import com.liverpool.gestion_pedidos.dto.CustomerDTO;

import java.util.List;

public interface ICustomerService {

    List<CustomerDTO> getCustomers();
    CustomerDTO getCustomerById(Long customerId);
    CustomerDTO createCustomer(CustomerDTO customerDTO);
    CustomerDTO updateCustomer(Long customerId, CustomerDTO customerDTO);
    void deleteCustomer(Long customerId);
}
