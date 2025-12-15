package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.ComandaCocina;

@Repository
public interface ComandaCocinaRepository extends JpaRepository<ComandaCocina, Integer> {
    
}
