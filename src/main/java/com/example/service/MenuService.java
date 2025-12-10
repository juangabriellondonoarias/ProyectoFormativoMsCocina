package com.example.service;

import com.example.models.Menu; 

import com.example.repository.MenuRepository;
import java.util.List;
import java.util.Optional;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



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
	
	
}
