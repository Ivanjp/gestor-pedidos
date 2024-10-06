package com.liverpool.gestion_pedidos.service;

import com.liverpool.gestion_pedidos.dto.CustomerDTO;

import java.util.List;

public interface ICustomerService {

    List<CustomerDTO> getCustomers();
    CustomerDTO getCustomerById(Long id);
    CustomerDTO createCustomer(CustomerDTO customer);
    CustomerDTO updateCustomer(Long id, CustomerDTO customer);
    void deleteCustomer(Long id);
}
