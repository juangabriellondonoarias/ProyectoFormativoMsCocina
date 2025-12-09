package com.exapmle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@RequestMapping("api/Menu")



public class MenuController {
	
	/*para enlazar nuestro servicio */
	@Autowired
	 private  MenuService MenuService;

	

	/*va a retornar una lista del menu*/
	@GetMapping
	public List<Menu> getAll(){
		return MenuService.getMenus();
	}
	
	@PostMapping
	public void save(@RequestBody Menu Menu){
		 MenuService.save(Menu);
	}
	
	/*eliminar que cuando se utilice el elemento */
	@DeleteMapping("/{idmenu}")
	
	
	public void save(@PathVariable("idmenu") Integer idmenu) {
		MenuService.delete(idmenu);
	}
	
}
