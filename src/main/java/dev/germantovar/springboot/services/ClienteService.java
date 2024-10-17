package dev.germantovar.springboot.services;

import dev.germantovar.springboot.repository.ClienteRepository;
import dev.germantovar.springboot.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    public List<Cliente> getAll(){
        return (List<Cliente>) clienteRepository.findAll();
    }



    // Método para buscar un cliente por correo
    public Cliente buscarClientePorCorreo(String correo) {
        return clienteRepository.findByCorreo(correo); // Debes tener este método en tu repositorio
    }

    // Método para contar total de clientes
    public Long countClientes() {
        return clienteRepository.count(); // Esto cuenta todos los clientes
    }

    // Método para guardar un nuevo cliente
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente); // Guarda el cliente en la base de datos
    }
}
