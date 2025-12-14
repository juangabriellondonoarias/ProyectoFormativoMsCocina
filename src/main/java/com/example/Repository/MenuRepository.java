package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.models.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    
}
