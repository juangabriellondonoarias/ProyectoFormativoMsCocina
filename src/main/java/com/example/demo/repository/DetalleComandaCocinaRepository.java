package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.DetalleComandaCocina;

@Repository
public interface DetalleComandaCocinaRepository extends JpaRepository<DetalleComandaCocina, Integer> {
    
}