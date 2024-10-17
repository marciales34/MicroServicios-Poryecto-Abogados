package dev.germantovar.springboot.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "casos")
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class CustomerCasosAbogados {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long id_abogado_encargado;
    private String id_cliente;
    private String estado_caso;
    private String descripcion_caso;
    private String evidencia;
    private String testimonios;
    private String creado_en;
    private String actualizado_en;
    private String rama_id;

    // Método para asignar automáticamente las fechas de creación y actualización
    @PrePersist
    protected void onCreate() {
        this.creado_en = String.valueOf(LocalDateTime.now());
    }

    @PreUpdate
    protected void onUpdate() {
        this.actualizado_en = String.valueOf(LocalDateTime.now());
    }

}

