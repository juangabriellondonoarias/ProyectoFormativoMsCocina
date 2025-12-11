package com.example.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.models.DetalleMenu;

@Repository

public interface DetalleMenuRepository extends JpaRepository<DetalleMenu, Integer> {

}
