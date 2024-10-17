package dev.germantovar.springboot.services;

import dev.germantovar.springboot.entities.Customer;
import dev.germantovar.springboot.entities.CustomerCasosAbogados;
import dev.germantovar.springboot.repository.CustomerRepositoryCasosAbogados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceCasosAbogados implements ICustomerServiceCasosAbogados {

    @Autowired
    private CustomerRepositoryCasosAbogados repository;

    @Override
    public List<CustomerCasosAbogados> getAll() {
        return (List<CustomerCasosAbogados>) repository.findAll();
    }

    @Override
    public CustomerCasosAbogados getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public CustomerCasosAbogados save(CustomerCasosAbogados customer) {
        return repository.save(customer);
    }

    @Override
    public long countCasos() {
        return repository.count();
    }
}
