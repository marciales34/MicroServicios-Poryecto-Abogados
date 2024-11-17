package dev.germantovar.springboot.repository;

import dev.germantovar.springboot.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByCorreo(String correo);

    Cliente findByCorreoAndContrasena(String correo, String contrasena);

}
