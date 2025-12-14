package com.example.controller;

import java.util.List;   
import java.util.Optional;
import java.util.Map;
import com.example.dto.MenuRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.Menu;
import com.example.service.MenuService;



@RestController
@RequestMapping("api/menu")
@Tag(name = "Menú Principal", description = "Gestión de los platos y elementos del menú de la cocina.")
public class MenuController {
	
	 private final MenuService menuService;
	 
	 public MenuController(MenuService menuService) {
		 this.menuService = menuService;
	 }
	
    @Operation(summary = "Obtener todos los menús", description = "Retorna una lista de todos los platos del menú.")
	@GetMapping
	public List<Menu> getAll(){
		return menuService.findAll();
	}
	
    @Operation(summary = "Crear nuevo menú", description = "Registra un nuevo plato en el menú de la cocina.")
	@PostMapping
    public ResponseEntity<Menu> save(@RequestBody MenuRequestDTO menuDto){ 
        
        Menu menuToSave = new Menu();
        menuToSave.setNombre(menuDto.getNombre());
        menuToSave.setPrecio_unitario(menuDto.getPrecio_unitario());
        // Si el DTO trae activo, lo usa, sino se usa el valor por defecto de la entidad
        if(menuDto.getActivo() != null) {
            menuToSave.setActivo(menuDto.getActivo());
        }
        
        Menu savedMenu = menuService.save(menuToSave);
        
        return new ResponseEntity<>(savedMenu, HttpStatus.CREATED);
    }
	
    @Operation(summary = "Eliminar menú por ID", description = "Elimina un plato del menú usando su ID.")
	@DeleteMapping("/{idmenu}")
	public void deleteById(@PathVariable("idmenu") Integer idmenu) {
		menuService.deleteById(idmenu);
	}
	
    @Operation(summary = "Consultar menú por ID", description = "Retorna los detalles de un plato específico.")
	@GetMapping("/{idmenu}")
	public Optional<Menu> getBId(@PathVariable("idmenu") Integer idmenu) {
		return menuService.findById(idmenu);
	}
	
    @Operation(summary = "Actualización total (PUT)", description = "Reemplaza completamente un plato existente. Se debe enviar el DTO completo.")
    @PutMapping("/{idmenu}")
    public ResponseEntity<Menu> actualizarMenu(@PathVariable("idmenu") Integer idmenu, @RequestBody MenuRequestDTO menuDto) {
        
        Menu menuToUpdate = new Menu();
        menuToUpdate.setIdmenu(idmenu);
        menuToUpdate.setNombre(menuDto.getNombre());
        menuToUpdate.setPrecio_unitario(menuDto.getPrecio_unitario());
        menuToUpdate.setActivo(menuDto.getActivo()); // Asumimos que activo es obligatorio en el PUT

        Menu menuActualizado = menuService.update(menuToUpdate);

        if (menuActualizado != null) {
            return ResponseEntity.ok(menuActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Actualización parcial de Nombre (PATCH)", description = "Actualiza solo el nombre del plato. Espera un JSON: {\"nombre\": \"Nuevo Nombre\"}.")
	@RequestMapping(value = "/{idmenu}/nombre", method = RequestMethod.PATCH)
	public ResponseEntity<Menu> actualizarNombre(@PathVariable("idmenu") Integer idmenu, @RequestBody Map<String, String> body) {
	    String nombre = body.get("nombre");
	    
	    if (nombre == null || nombre.trim().isEmpty()) { 
	        return ResponseEntity.badRequest().body(null);
	    }
	    
	    Menu menuActualizado = menuService.actualizarCampo(idmenu, "nombre", nombre);

	    if (menuActualizado != null) {
	        return ResponseEntity.ok(menuActualizado);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
    
    @Operation(summary = "Actualización parcial de Precio (PATCH)", description = "Actualiza solo el precio unitario. Espera un JSON: {\"precio_unitario\": 12.50}.")
	@RequestMapping(value = "/{idmenu}/precio", method = RequestMethod.PATCH)
	public ResponseEntity<Menu> actualizarPrecio(@PathVariable("idmenu") Integer idmenu, @RequestBody Map<String, Double> body) {
	    Double precio = body.get("precio_unitario");
	    
	    if (precio == null || precio <= 0) { 
	        return ResponseEntity.badRequest().body(null);
	    }
	    
	    Menu menuActualizado = menuService.actualizarCampo(idmenu, "precio_unitario", precio);

	    if (menuActualizado != null) {
	        return ResponseEntity.ok(menuActualizado);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
    @Operation(summary = "Actualización parcial de Activo (PATCH)", description = "Actualiza solo el estado activo/inactivo. Espera un JSON: {\"activo\": false}.")
	@RequestMapping(value = "/{idmenu}/activo", method = RequestMethod.PATCH)
	public ResponseEntity<Menu> actualizarActivo(@PathVariable("idmenu") Integer idmenu, @RequestBody Map<String, Boolean> body) {
	    Boolean activo = body.get("activo");
	    
	    if (activo == null) { 
	        return ResponseEntity.badRequest().body(null);
	    }
	    
	    Menu menuActualizado = menuService.actualizarCampo(idmenu, "activo", activo);

	    if (menuActualizado != null) {
	        return ResponseEntity.ok(menuActualizado);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
}