package dev.germantovar.springboot.repository;

import dev.germantovar.springboot.entities.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findByCorreoAndPassword(String correo, String password);
}
