package dev.germantovar.springboot.controllers;

import dev.germantovar.springboot.entities.Cliente;
import dev.germantovar.springboot.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap; // Importa HashMap
import java.util.List;
import java.util.Map; // Importa Map
import java.time.LocalDateTime;

@RestController
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/Clientes")
    public List<Cliente> getAll() {
        return clienteService.getAll(); // Asegúrate de que estás llamando al servicio correcto
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
}
