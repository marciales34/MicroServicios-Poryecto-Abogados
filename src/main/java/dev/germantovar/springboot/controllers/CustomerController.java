package dev.germantovar.springboot.controllers;

import dev.germantovar.springboot.entities.Customer;
import dev.germantovar.springboot.repository.CustomerRepository;
import dev.germantovar.springboot.services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class CustomerController {
    @Autowired
    private ICustomerService service;

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/Abogados")
    public List<Customer> getAll() {
        return service.getAll();
    }

    @PostMapping("/Enviar")
    public ResponseEntity<Map<String, Object>> save(@RequestBody Customer customer) {
        customer.setRol("abogado");
        customer.setCreated_at(LocalDateTime.now().toString());
        customer.setUpdated_at(LocalDateTime.now().toString());

        try {
            Customer savedCustomer = service.save(customer);
            Map<String, Object> response = new HashMap<>();
            response.put("id", savedCustomer.getId());
            response.put("message", "Registro exitoso");
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

    @GetMapping("/Abogados/{id}")
    public ResponseEntity<Object> getAbogadoById(@PathVariable Long id) {
        Optional<Customer> abogado = customerRepository.findById(id);
        if (abogado.isPresent()) {
            return ResponseEntity.ok(abogado.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Customer loginRequest) {
        Customer customer = customerRepository.findByCorreoAndPassword(loginRequest.getCorreo(), loginRequest.getPassword());
        if (customer != null) {
            Map<String, String> response = new HashMap<>();
            response.put("nombre", customer.getNombre());
            response.put("id", String.valueOf(customer.getId()));
            response.put("rol", customer.getRol());
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @GetMapping("/totalAbogados")
    public ResponseEntity<Long> getTotalAbogados() {
        return ResponseEntity.ok(service.countAbogados());
    }
    @PostMapping("/crearAbogado")
    public ResponseEntity<Map<String, Object>> crearAbogado(@RequestBody Customer abogado) {
        abogado.setRol("abogado");
        abogado.setCreated_at(LocalDateTime.now().toString());
        abogado.setUpdated_at(LocalDateTime.now().toString());

        try {
            Customer savedAbogado = service.save(abogado);
            Map<String, Object> response = new HashMap<>();
            response.put("id", savedAbogado.getId());
            response.put("message", "Abogado registrado exitosamente");
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
