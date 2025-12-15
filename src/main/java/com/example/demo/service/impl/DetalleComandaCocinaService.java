package com.example.demo.service.impl;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.DetalleComandaCocina;
import com.example.demo.repository.DetalleComandaCocinaRepository;

@Service
public class DetalleComandaCocinaService {

	@Autowired
	private DetalleComandaCocinaRepository detalleComandaCocinaRepository;
	
	/*para guardar y actualizar*/
	@Transactional
	public DetalleComandaCocina save(DetalleComandaCocina detalle) {
		return detalleComandaCocinaRepository.save(detalle);
	}
	
	// Método para obtener todos los detalles de comanda
    // Usamos (readOnly = true) porque es solo lectura, y mantiene la sesión abierta 
    // para manejar la relación EAGER/LAZY si fuera necesario.
	
	@Transactional(readOnly = true)
	public List<DetalleComandaCocina> findAll(){
		return detalleComandaCocinaRepository.findAll();	
		}
	
	/*metodo para obtener por el id*/
	@Transactional (readOnly = true)
	public DetalleComandaCocina findById(Integer id) {
		return detalleComandaCocinaRepository.findById(id).orElse(null);
	}
	
	
	/*elimiar el id*/
	
	@Transactional
	public void deleteById(Integer id) {
		detalleComandaCocinaRepository.deleteById(id);
	}
	
	/*actualizar*/
	
	@Transactional
	public DetalleComandaCocina actualizarCantidad(Integer id, Integer cantidad) {
	    // 1. Buscar el Detalle Comanda existente por ID
	    DetalleComandaCocina detalleExistente = detalleComandaCocinaRepository.findById(id).orElse(null);
	    
	    if (detalleExistente != null) {
	        // 2. Modificar solo el campo 'cantidad'
	        
	        // ¡Importante! Asegúrate de que el setter en DetalleComandaCocina.java se llama setCantidad
	        detalleExistente.setCantidad(cantidad); 
	        
	        // 3. Guardar el objeto actualizado
	        return detalleComandaCocinaRepository.save(detalleExistente);
	    }
	    // Si no se encuentra, devuelve null
	    return null;
	}
	
	}
