package com.example.controller;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.DetalleMenu;
import com.example.service.DetalleMenuService;



/*import de swagger*/
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/detalle-menu")
@Tag(name = "Detalles de Menú", description = "Gestión de los ingredientes, imágenes o componentes de un plato del menú.")
public class DetalleMenuController {

	@Autowired
	private DetalleMenuService detalleMenuService;
	
	/*crear*/
	@Operation(summary = "Crear nuevo detalle de menú", description = "Añade un componente o ingrediente a un plato del menú.")
	@PostMapping
	public ResponseEntity<DetalleMenu> crearDetalle(@RequestBody DetalleMenu detalle){
		DetalleMenu nuevoDetalle = detalleMenuService.save(detalle);
		return ResponseEntity.ok(nuevoDetalle);
	}
	
	/*obtener todos */
	@Operation(summary = "Obtener todos los detalles de menú", description = "Retorna una lista de todos los componentes de los platos del menú.")
	@GetMapping
	public List<DetalleMenu> obtenerTodo() {
		return detalleMenuService.findAll();
	}
	
	/*obtener por el id*/
	@Operation(summary = "Obtener detalle por ID", description = "Retorna el detalle de un plato específico usando su ID.")
	@GetMapping("/{id}")
	public ResponseEntity<DetalleMenu> obtenerId(@PathVariable Integer id) {
		DetalleMenu detalle = detalleMenuService.findById(id);
		if(detalle != null) {
			return ResponseEntity.ok(detalle);
		}
		return ResponseEntity.notFound().build();
	}
	
	/*eliminar por el id*/
	@Operation(summary = "Eliminar detalle por ID", description = "Elimina un componente de un plato usando su ID.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar (@PathVariable Integer id) {
		detalleMenuService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
