package com.example.controller;

import java.util.List;   
import java.util.Optional;
import java.util.Map;

import com.example.dto.MenuRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.models.Menu;
import com.example.service.MenuService;


/*import de swagger*/
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController

/*nuestra ruta*/

@RequestMapping("api/menu")
@Tag(name = "Menú Principal", description = "Gestión de los platos y elementos del menú de la cocina.")
public class MenuController {
	
	/*para enlazar nuestro servicio */
	//@Autowired
	 private final MenuService menuService;
	 
	 public MenuController(MenuService menuService) {
		 this.menuService = menuService;
	 }
	
	

	/*va a retornar una lista del menu*/
	@Operation(summary = "Obtener todos los menús", description = "Retorna una lista de todos los platos del menú.") 
	@GetMapping
	public List<Menu> getAll(){
		return menuService.findAll();
	}
	@Operation(summary = "Crear nuevo menú", description = "Registra un nuevo plato en el menú de la cocina.")
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
	@Operation(summary = "Eliminar menú por ID", description = "Elimina un plato del menú usando su ID.")
	@DeleteMapping("/{idmenu}")
	
	/*por la ruta resive una variable idmenu*/
	public void deleteById(@PathVariable("idmenu") Integer idmenu) {
		menuService.deleteById(idmenu);
	}
	
	/*consultar */
	@Operation(summary = "Consultar menú por ID", description = "Retorna los detalles de un plato específico.")
	@GetMapping("/{idmenu}")
	public Optional<Menu> getBId(@PathVariable("idmenu") Integer idmenu) {
		return menuService.findById(idmenu);
	}
	
	
	/*actualizar todo*/
	@Operation(summary = "Actualización total (PUT)", description = "Reemplaza completamente un plato existente. Se debe enviar el objeto completo (nombre, precio, activo).")
	@PutMapping("/{idmenu}")
    public ResponseEntity<Menu> actualizarMenu(@PathVariable("idmenu") Integer idmenu, @RequestBody MenuRequestDTO menuDto) {
        
        // Creamos la entidad con el ID para indicar a JPA que es una actualización
        Menu menuToUpdate = new Menu();
        menuToUpdate.setIdmenu(idmenu); // Establecer el ID desde la URL
        menuToUpdate.setNombre(menuDto.getNombre());
        menuToUpdate.setPrecio_unitario(menuDto.getPrecio_unitario());
        // El campo 'activo' debe ser manejado en la DTO si quieres que se actualice

        Menu menuActualizado = menuService.update(menuToUpdate); // Llamada al nuevo método del servicio

        if (menuActualizado != null) {
            return ResponseEntity.ok(menuActualizado);
        } else {
            // Esto ocurre si el ID del menú no existe
            return ResponseEntity.notFound().build();
        }
    }
	
	/*actualizar uno por uno*/
	@Operation(summary = "Actualización parcial de Nombre ", description = "Actualiza solo el nombre de un plato")
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
    
	@Operation(summary = "Actualización parcial de Precio ", description = "Actualiza solo el precio unitario de un plato")
	@RequestMapping(value = "/{idmenu}/precio", method = RequestMethod.PATCH)
	public ResponseEntity<Menu> actualizarPrecio(@PathVariable("idmenu") Integer idmenu, @RequestBody Map<String, Double> body) {
	    Double precio = body.get("precio_unitario"); // Nota: Usamos "precio_unitario" como clave JSON
	    
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
	
	@Operation(summary = "Actualización parcial de Activo (PATCH)", description = "Actualiza solo el estado activo/inactivo  de un plato")
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

