package dev.germantovar.springboot.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "clientes")
@Setter
@Getter
@ToString
@EqualsAndHashCode

public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String correo;
    private String contrasena;
    private String edad;
    private String telefono;
    private String direccion;
    private String created_at;
    private String updated_at;

    // Método para asignar automáticamente las fechas de creación y actualización
    @PrePersist
    protected void onCreate() {
        this.created_at = String.valueOf(LocalDateTime.now());
    }

    @PreUpdate
    protected void onUpdate() {
        this.updated_at = String.valueOf(LocalDateTime.now());
    }


}
