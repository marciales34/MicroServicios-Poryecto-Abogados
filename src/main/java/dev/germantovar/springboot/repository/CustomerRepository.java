package dev.germantovar.springboot.repository;

import dev.germantovar.springboot.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByCorreoAndPassword(String correo, String password);
}
