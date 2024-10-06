package com.liverpool.gestion_pedidos.service.impl;

import com.liverpool.gestion_pedidos.dto.CustomerDTO;
import com.liverpool.gestion_pedidos.exception.ApiException;
import com.liverpool.gestion_pedidos.model.CustomerModel;
import com.liverpool.gestion_pedidos.repository.ICustomerRepository;
import com.liverpool.gestion_pedidos.service.ICustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    ICustomerRepository repository;

    @Override
    public List<CustomerDTO> getCustomers() {

        List<CustomerModel> customerModel = repository.findAll();

        return customerModel.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {

        CustomerModel customerModel = repository.findById(id).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Customer not found"));
        return convertToDTO(customerModel);

    }

    @Override
    public CustomerDTO createCustomer(@Valid CustomerDTO customer) {

        CustomerModel customerModel = convertToModel(customer);

        CustomerModel newCustomer = repository.save(customerModel);

        return convertToDTO(newCustomer);

    }

    @Override
    public CustomerDTO updateCustomer(Long id, @Valid CustomerDTO customer) {

        CustomerModel customerModel = repository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Customer not found"));

        if (customer.getNombre() != null) {
            customerModel.setNombre(customer.getNombre());
        }

        customerModel.setApellidoMaterno(customer.getApellidoMaterno());

        if (customer.getApellidoPaterno() != null) {
            customerModel.setApellidoPaterno(customer.getApellidoPaterno());
        }

        customerModel.setMail(customer.getMail());

        if (customer.getDireccionEnvio() != null) {
            customerModel.setDireccionEnvio(customer.getDireccionEnvio());
        }

        CustomerModel updatedCustomer = repository.save(customerModel);

        return convertToDTO(updatedCustomer);


    }

    @Override
    public void deleteCustomer(Long id) {

        if (!repository.existsById(id)) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Customer not found");
        }

        repository.deleteById(id);
    }

    private CustomerDTO convertToDTO(CustomerModel model){
        CustomerDTO dto = new CustomerDTO();

        dto.setId(model.getId());
        dto.setNombre(model.getNombre());
        dto.setApellidoMaterno(model.getApellidoMaterno());
        dto.setApellidoPaterno(model.getApellidoPaterno());
        dto.setMail(model.getMail());
        dto.setDireccionEnvio(model.getDireccionEnvio());

        return dto;

    }

    private CustomerModel convertToModel(CustomerDTO dto){
        CustomerModel model = new CustomerModel();

        model.setNombre(dto.getNombre());
        model.setApellidoMaterno(dto.getApellidoMaterno());
        model.setApellidoPaterno(dto.getApellidoPaterno());
        model.setMail(dto.getMail());
        model.setDireccionEnvio(dto.getDireccionEnvio());

        return model;

    }

}
