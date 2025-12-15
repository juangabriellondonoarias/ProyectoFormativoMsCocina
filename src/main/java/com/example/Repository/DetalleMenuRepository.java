package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.models.DetalleMenu;

@Repository
public interface DetalleMenuRepository extends JpaRepository<DetalleMenu, Integer> {
   
}