package dev.germantovar.springboot.services;

import dev.germantovar.springboot.entities.Cliente;

import java.util.List;

public interface ICliente {
    List<Cliente> getAll(); // Obtener todos los clientes
}
