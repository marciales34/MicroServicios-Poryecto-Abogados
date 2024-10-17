package dev.germantovar.springboot.services;

import dev.germantovar.springboot.entities.Customer;
import dev.germantovar.springboot.entities.CustomerCasosAbogados;

import java.util.List;

public interface ICustomerServiceCasosAbogados {

    List<CustomerCasosAbogados> getAll(); // Obtener todos los casos
    CustomerCasosAbogados getById(Long id); // Obtener caso por ID
    CustomerCasosAbogados save(CustomerCasosAbogados customer); // Guardar un nuevo caso
    long countCasos(); // Agrega esta línea
}
