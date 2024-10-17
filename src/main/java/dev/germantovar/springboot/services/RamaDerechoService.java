package dev.germantovar.springboot.services;

import dev.germantovar.springboot.entities.RamaDerecho;
import dev.germantovar.springboot.repository.RamaDerechoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RamaDerechoService {

    @Autowired
    private RamaDerechoRepository ramaDerechoRepository;

    public List<RamaDerecho> obtenerRamasDerecho() {
        return ramaDerechoRepository.findAll();


    }
}
