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

    @Autowired
    private ClienteRepository ClienteRepository;

    public Cliente autenticarCliente(String correo, String password) {
        Cliente cliente = clienteRepository.findByCorreo(correo); // Busca el cliente por correo
        if (cliente != null && cliente.getContrasena().equals(password)) { // Verifica la contraseña
            return cliente;
        }
        return null; // Devuelve null si el cliente no existe o la contraseña es incorrecta
    }
    public Cliente registrarCliente(Cliente cliente) throws Exception {
        // Verifica si el correo ya está registrado
        if (clienteRepository.findByCorreo(cliente.getCorreo()) != null) {
            throw new Exception("El correo ya está registrado");
        }

        // Guarda el cliente en la base de datos
        return clienteRepository.save(cliente);
    }
}
