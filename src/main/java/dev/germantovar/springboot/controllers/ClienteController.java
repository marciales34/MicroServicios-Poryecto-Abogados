package dev.germantovar.springboot.controllers;

import dev.germantovar.springboot.entities.Cliente;
import dev.germantovar.springboot.repository.ClienteRepository;
import dev.germantovar.springboot.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository; // Usar esta variable correctamente

    @GetMapping("/Clientes")
    public List<Cliente> getAll() {
        return clienteService.getAll();
    }

    @GetMapping("/buscarCliente")
    public ResponseEntity<?> buscarCliente(@RequestParam String correo) {
        Cliente cliente = clienteService.buscarClientePorCorreo(correo);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.status(404).body("Cliente no encontrado con el correo: " + correo);
        }
    }

    @GetMapping("/totalClientes")
    public ResponseEntity<Long> getTotalClientes() {
        return ResponseEntity.ok(clienteService.countClientes());
    }

    @PostMapping("/crearCliente")
    public ResponseEntity<Object> crearCliente(@RequestBody Cliente cliente) {
        cliente.setCreated_at(LocalDateTime.now().toString());
        cliente.setUpdated_at(LocalDateTime.now().toString());

        try {
            Cliente savedCliente = clienteService.save(cliente);
            Map<String, Object> response = new HashMap<>();
            response.put("id", savedCliente.getId());
            response.put("message", "Cliente registrado exitosamente");
            return ResponseEntity.ok(response);
        } catch (DataIntegrityViolationException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "El correo ya está registrado");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Error interno del servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/Clientes/{id}")
    public ResponseEntity<Object> updateCliente(@PathVariable Long id, @RequestBody Cliente clienteActualizado) {
        if (!clienteRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado con el id: " + id);
        }
        clienteActualizado.setId(id);
        clienteActualizado.setUpdated_at(LocalDateTime.now().toString());
        Cliente clienteGuardado = clienteRepository.save(clienteActualizado);
        return ResponseEntity.ok(clienteGuardado);
    }

    @DeleteMapping("/Clientes/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        if (!clienteRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        clienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
//Clientes Camilo:S
    @PostMapping("/login-clientes")
    public ResponseEntity<Map<String, String>> login(@RequestBody Cliente loginRequest) {
        // Usar clienteRepository aquí, no customerRepository
        Cliente customer = clienteRepository.findByCorreoAndContrasena(loginRequest.getCorreo(), loginRequest.getContrasena());
        if (customer != null) {
            Map<String, String> response = new HashMap<>();
            response.put("nombre", customer.getNombre());
            response.put("id", String.valueOf(customer.getId()));
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @PostMapping("/RegistrarCliente")
    public ResponseEntity<Object> registrarCliente(@RequestBody Cliente cliente) {
        cliente.setCreated_at(LocalDateTime.now().toString());
        cliente.setUpdated_at(LocalDateTime.now().toString());

        try {
            Cliente nuevoCliente = clienteService.save(cliente);
            Map<String, Object> response = new HashMap<>();
            response.put("id", nuevoCliente.getId());
            response.put("token", "token_de_autenticacion");
            response.put("message", "Cliente registrado con éxito");
            return ResponseEntity.ok(response);
        } catch (DataIntegrityViolationException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "El correo ya está registrado");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Error interno del servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
