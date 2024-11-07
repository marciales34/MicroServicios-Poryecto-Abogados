package dev.germantovar.springboot.services;

import dev.germantovar.springboot.entities.Customer;
import java.util.List;

public interface ICustomerService {
    List<Customer> getAll();

}
