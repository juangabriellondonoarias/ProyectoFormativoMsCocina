package com.example.controller;

import java.util.List;  
import java.util.Optional;
import com.example.dto.MenuRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.models.Menu;
import com.example.service.MenuService;



@RestController

/*nuestra ruta*/

@RequestMapping("api/menu")




public class MenuController {
	
	/*para enlazar nuestro servicio */
	//@Autowired
	 private final MenuService menuService;
	 
	 public MenuController(MenuService menuService) {
		 this.menuService = menuService;
	 }
	
	

	/*va a retornar una lista del menu*/
	@GetMapping
	public List<Menu> getAll(){
		return menuService.findAll();
	}
	
	@PostMapping
public ResponseEntity<Menu> save(@RequestBody MenuRequestDTO menuDto){ 
        
        // 1. Convertir DTO a Entidad
        Menu menuToSave = new Menu();
        menuToSave.setNombre(menuDto.getNombre());
        menuToSave.setPrecio_unitario(menuDto.getPrecio_unitario());
        // El campo 'activo' se mantiene como true por defecto en la entidad
        
        // 2. Guardar la Entidad
        Menu savedMenu = menuService.save(menuToSave);
        
        // 3. Retornar la Entidad guardada
        return new ResponseEntity<>(savedMenu, HttpStatus.CREATED);
    }
	
	/*eliminar que cuando se utilice el elemento */
	@DeleteMapping("/{idmenu}")
	
	/*por la ruta resive una variable idmenu*/
	public void deleteById(@PathVariable("idmenu") Integer idmenu) {
		menuService.deleteById(idmenu);
	}
	
	/*consultar */
	
	@GetMapping("/{idmenu}")
	public Optional<Menu> getBId(@PathVariable("idmenu") Integer idmenu) {
		return menuService.findById(idmenu);
	}
	
	
}

