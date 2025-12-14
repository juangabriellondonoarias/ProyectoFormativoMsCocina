package com.example.service;

import java.util.List;  

import javax.swing.plaf.basic.BasicComboBoxUI.ListDataHandler;
import com.example.models.EstadoComanda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.models.ComandaCocina;
import com.example.repository.ComandaCocinaRepository;

//import jakarta.transaction.TransactionScoped;

@Service
public class ComandaCocinaService {

	@Autowired
	private ComandaCocinaRepository comandaCocinaRepository;
	
	/*metodo para guardar o actualizar la comanda*/
	@Transactional
	public ComandaCocina save(ComandaCocina comanda) {
		return comandaCocinaRepository.save(comanda);
	}
	
	
	
	/*metodo para obtener por el id*/
	@Transactional(readOnly = true)
	public ComandaCocina findById(Integer id) {
		return comandaCocinaRepository.findById(id).orElse(null);
	}
	
	/*para obtener todas*/
	
	@Transactional(readOnly = true)
	public List<ComandaCocina> findAll(){
		return comandaCocinaRepository.findAll();	
		}
	
	/*metodo para eliminar por el id*/
	
	@Transactional
	public void deleteById(Integer id) {
		comandaCocinaRepository.deleteById(id);
	}
	
	@Transactional
    public ComandaCocina actualizarEstado(Integer id, EstadoComanda nuevoEstado) {
        // 1. Buscar la comanda existente por ID
        ComandaCocina comandaExistente = comandaCocinaRepository.findById(id).orElse(null);
        
        if (comandaExistente != null) {
            // 2. Modificar solo el campo de estado
            comandaExistente.setEstado(nuevoEstado);
            
            // 3. Guardar el objeto actualizado (Hibernate sabe que solo el campo 'estado' cambió)
            return comandaCocinaRepository.save(comandaExistente);
        }
        // Si no se encuentra, devuelve null
        return null;
    }
	
	@Transactional
	public ComandaCocina actualizarNotas(Integer id, String notas) {
	    ComandaCocina comandaExistente = comandaCocinaRepository.findById(id).orElse(null);
	    
	    if (comandaExistente != null) {
	        // Asegúrate de que setNotas(String) existe en tu entidad ComandaCocina.java
	        comandaExistente.setNotas(notas); 
	        return comandaCocinaRepository.save(comandaExistente);
	    }
	    return null;
	}
	
	
	@Transactional
	public ComandaCocina update(ComandaCocina comanda) {
	    // El PUT requiere que el ID exista para actualizar
	    if (comandaCocinaRepository.existsById(comanda.getIdComandaCocina())) {
	        // save() con un ID existente realiza el UPDATE en JPA
	        return comandaCocinaRepository.save(comanda);
	    }
	    // Si el ID no existe, devuelve null
	    return null;
	}
	
	
	
}