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
    public CustomerDTO getCustomerById(Long customerId) {

        CustomerModel customerModel = repository.findById(customerId).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Customer not found"));
        return convertToDTO(customerModel);

    }

    @Override
    public CustomerDTO createCustomer(@Valid CustomerDTO customerDTO) {

        CustomerModel customerModel = convertToModel(customerDTO);

        CustomerModel newCustomer = repository.save(customerModel);

        return convertToDTO(newCustomer);

    }

    @Override
    public CustomerDTO updateCustomer(Long customerId, @Valid CustomerDTO customerDTO) {

        CustomerModel customerModel = repository.findById(customerId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Customer not found"));

        if (customerDTO.getNombre() != null) {
            customerModel.setNombre(customerDTO.getNombre());
        }

        customerModel.setApellidoMaterno(customerDTO.getApellidoMaterno());

        if (customerDTO.getApellidoPaterno() != null) {
            customerModel.setApellidoPaterno(customerDTO.getApellidoPaterno());
        }

        customerModel.setMail(customerDTO.getMail());

        if (customerDTO.getDireccionEnvio() != null) {
            customerModel.setDireccionEnvio(customerDTO.getDireccionEnvio());
        }

        CustomerModel updatedCustomer = repository.save(customerModel);

        return convertToDTO(updatedCustomer);


    }

    @Override
    public void deleteCustomer(Long customerId) {

        if (!repository.existsById(customerId)) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Customer not found");
        }

        repository.deleteById(customerId);
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
