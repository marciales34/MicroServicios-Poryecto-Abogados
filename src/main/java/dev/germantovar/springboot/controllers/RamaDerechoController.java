package dev.germantovar.springboot.controllers;

import dev.germantovar.springboot.entities.RamaDerecho;
import dev.germantovar.springboot.services.RamaDerechoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RamaDerechoController {

    @Autowired
    private RamaDerechoService ramaDerechoService;

    @GetMapping("/ramasDerecho")
    public List<RamaDerecho> obtenerRamasDerecho() {
        return ramaDerechoService.obtenerRamasDerecho();
    }
}

