package dev.germantovar.springboot.services;

import dev.germantovar.springboot.entities.Customer;
import dev.germantovar.springboot.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService{

    @Autowired
    private CustomerRepository repository;
    public List<Customer> getAll(){
        return (List<Customer>) repository.findAll();
    }

    public Customer save (Customer customer){
        return  repository.save(customer);

    }public long countAbogados() {
        return repository.count(); // Asegúrate de tener un 'repository' para abogados
    }
}
