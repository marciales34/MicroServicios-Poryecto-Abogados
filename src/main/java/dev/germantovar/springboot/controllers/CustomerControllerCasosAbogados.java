package dev.germantovar.springboot.controllers;

import dev.germantovar.springboot.entities.CustomerCasosAbogados;
import dev.germantovar.springboot.repository.CustomerRepositoryCasosAbogados;
import dev.germantovar.springboot.services.ICustomerServiceCasosAbogados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerControllerCasosAbogados {

    @Autowired
    private CustomerRepositoryCasosAbogados customerCasosAbogadosRepository;

    @Autowired
    private ICustomerServiceCasosAbogados service;

    @GetMapping("/casos") // Cambié la "C" a minúscula para seguir la convención
    public List<CustomerCasosAbogados> getAll() {
        return service.getAll();
    }

    @GetMapping("/casos/{id}")
    public ResponseEntity<List<CustomerCasosAbogados>> getCasosByAbogadoId(@PathVariable Long id) {
        List<CustomerCasosAbogados> casos = customerCasosAbogadosRepository.findByAbogadoId(id);
        return !casos.isEmpty() ? ResponseEntity.ok(casos) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("/admin") // Endpoint para que el administrador vea todos los casos
    public ResponseEntity<List<CustomerCasosAbogados>> getAllCasosAdmin() {
        List<CustomerCasosAbogados> casos = service.getAll(); // Llama al servicio para obtener todos los casos
        return !casos.isEmpty() ? ResponseEntity.ok(casos) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/registra-casos") // Cambié a minúsculas y guiones
    public ResponseEntity<Object> createCaso( @RequestBody CustomerCasosAbogados nuevoCaso) {
        try {
            CustomerCasosAbogados casoGuardado = customerCasosAbogadosRepository.save(nuevoCaso);
            return ResponseEntity.status(HttpStatus.CREATED).body(casoGuardado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el caso: " + e.getMessage());
        }
    }

    @GetMapping("/total-casos")
    public ResponseEntity<Long> getTotalCasos() {
        return ResponseEntity.ok(service.countCasos());
    }

    @PutMapping("/casos/{id}")
    public ResponseEntity<Object> updateCaso(@PathVariable Long id, @RequestBody CustomerCasosAbogados casoActualizado) {
        if (!customerCasosAbogadosRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        casoActualizado.setId(id);
        CustomerCasosAbogados casoGuardado = customerCasosAbogadosRepository.save(casoActualizado);
        return ResponseEntity.ok(casoGuardado);
    }

    @DeleteMapping("/casos/{id}")
    public ResponseEntity<Void> deleteCaso(@PathVariable Long id) {
        if (!customerCasosAbogadosRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        customerCasosAbogadosRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
