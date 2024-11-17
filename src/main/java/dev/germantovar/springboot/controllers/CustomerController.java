package dev.germantovar.springboot.controllers;

import dev.germantovar.springboot.entities.Customer;
import dev.germantovar.springboot.repository.CustomerRepository;
import dev.germantovar.springboot.services.CustomerService;
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
    private CustomerService service;

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/Abogados")
    public List<Customer> getAll() {
        return service.getAll();
    }

    @PostMapping("/Enviar")
    public ResponseEntity<Map<String, Object>> save(@RequestBody Customer customer) {
        if (customer.getRol() == null || (!customer.getRol().equals("abogado") && !customer.getRol().equals("admin"))) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Rol inválido. Debe ser 'abogado' o 'admin'.");
            return ResponseEntity.badRequest().body(response);
        }
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


    @DeleteMapping("/Abogados/{id}")
    public ResponseEntity<Void> deleteAbogado(@PathVariable Long id) {
        if (!customerRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        customerRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // **Método PUT para actualizar un Abogado (Customer)**
    @PutMapping("/Abogados/{id}")
    public ResponseEntity<Object> updateAbogado(@PathVariable Long id, @RequestBody Customer customerUpdated) {
        // Verificar si el abogado (Customer) existe
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if (!existingCustomer.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Abogado no encontrado con el id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // Establecer el id del cliente para asegurarse de que se actualiza el correcto
        Customer customer = existingCustomer.get();
        customer.setNombre(customerUpdated.getNombre() != null ? customerUpdated.getNombre() : customer.getNombre());
        customer.setCorreo(customerUpdated.getCorreo() != null ? customerUpdated.getCorreo() : customer.getCorreo());
        customer.setPassword(customerUpdated.getPassword() != null ? customerUpdated.getPassword() : customer.getPassword());
        customer.setRol(customerUpdated.getRol() != null ? customerUpdated.getRol() : customer.getRol());
        customer.setUpdated_at(LocalDateTime.now().toString());  // Actualizamos la fecha de actualización

        try {
            // Guardamos el cliente actualizado
            Customer savedCustomer = customerRepository.save(customer);

            // Responder con el cliente actualizado
            return ResponseEntity.ok(savedCustomer);
        } catch (DataIntegrityViolationException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Error de integridad de datos. Asegúrese de que el correo no esté duplicado.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Error interno del servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}




