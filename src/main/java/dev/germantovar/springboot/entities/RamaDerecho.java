package dev.germantovar.springboot.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ramas")
public class RamaDerecho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_rama", nullable = false)
    private String nombreRama;

    @Column(name = "creado_en")
    private LocalDateTime creadoEn;

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreRama() {
        return nombreRama;
    }

    public void setNombreRama(String nombreRama) {
        this.nombreRama = nombreRama;
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(LocalDateTime creadoEn) {
        this.creadoEn = creadoEn;
    }
}
