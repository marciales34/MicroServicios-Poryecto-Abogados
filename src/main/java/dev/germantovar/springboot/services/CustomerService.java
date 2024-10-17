package dev.germantovar.springboot.services;

import dev.germantovar.springboot.entities.Customer;
import dev.germantovar.springboot.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository repository;

    @Override
    public List<Customer> getAll(){
        return (List<Customer>) repository.findAll();
    }

    @Override
    public Customer save(Customer customer){
        repository.save(customer);
        return customer;
    }

    @Override // Asegúrate de agregar esta anotación para el método
    public long countAbogados() {
        return repository.count(); // Asegúrate de tener un 'repository' para abogados
    }
}
