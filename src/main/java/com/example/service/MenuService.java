package com.example.service;

import com.example.models.Menu; 

import com.example.repository.MenuRepository;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service


public class MenuService {

	/*hacemos una inyeccion */
	
	//@Autowired
	
	/*llamamos los repositorio / lo definimos*/
	private final MenuRepository menuRepository;
	 
	public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }
	
	/*crear una serie de servicios*/
	public List<Menu> findAll (){
		
		/*findAll lo que hace es buscar todos los elementos y retornarlos*/
		return menuRepository.findAll();
		
	}
	
	/*ahora se va a buscar através del id*/
	
	public Optional<Menu> findById (Integer idmenu){
		return menuRepository.findById(idmenu);
	}
	
	/*guardar o actualizar*/
	
	public Menu save(Menu menu) {
		
		return menuRepository.save(menu);
	}
	
	/*eliminar*/
	
	public void deleteById(Integer idmenu) {
		menuRepository.deleteById(idmenu);
	}
	
	
	@Transactional
	public Menu update(Menu menu) {
	    // 1. Verificar si la entidad con ese ID existe
	    if (menuRepository.existsById(menu.getIdmenu())) {
	        // Si existe, la llamada a .save() con un ID existente realiza una actualización (UPDATE)
	        return menuRepository.save(menu);
	    }
	    // Si no existe, devuelve null para que el controlador pueda responder 404
	    return null;
	}
	
	
	@Transactional
	public Menu actualizarCampo(Integer id, String campo, Object valor) {
	    Menu menuExistente = menuRepository.findById(id).orElse(null);
	    
	    if (menuExistente != null) {
	        try {
	            // Genera el nombre del setter a partir del nombre del campo (ej: "nombre" -> "setNombre")
	            String setterName = "set" + campo.substring(0, 1).toUpperCase() + campo.substring(1);
	            
	            // Busca el método setter en la clase Menu
	            // La clase del valor determinará el tipo de argumento del setter
	            Method setter = menuExistente.getClass().getMethod(setterName, valor.getClass());
	            
	            // Invoca el setter en el objeto MenuExistente
	            setter.invoke(menuExistente, valor);
	            
	            return menuRepository.save(menuExistente);
	            
	        } catch (Exception e) {
	            // Maneja excepciones si el campo no existe, si el tipo es incorrecto o si el método falla.
	            // Para fines de depuración: e.printStackTrace();
	            System.err.println("Error al actualizar campo: " + e.getMessage());
	            return null;
	        }
	    }
	    return null;
	}
	
	
}
