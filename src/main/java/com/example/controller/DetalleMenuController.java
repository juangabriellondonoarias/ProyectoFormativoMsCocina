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


@RestController
@RequestMapping("/api/detalle-menu")
public class DetalleMenuController {

	@Autowired
	private DetalleMenuService detalleMenuService;
	
	/*crear*/
	@PostMapping
	public ResponseEntity<DetalleMenu> crearDetalle(@RequestBody DetalleMenu detalle){
		DetalleMenu nuevoDetalle = detalleMenuService.save(detalle);
		return ResponseEntity.ok(nuevoDetalle);
	}
	
	/*obtener todos */
	@GetMapping
	public List<DetalleMenu> obtenerTodo() {
		return detalleMenuService.findAll();
	}
	
	/*obtener por el id*/
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalleMenu> obtenerId(@PathVariable Integer id) {
		DetalleMenu detalle = detalleMenuService.findById(id);
		if(detalle != null) {
			return ResponseEntity.ok(detalle);
		}
		return ResponseEntity.notFound().build();
	}
	
	/*eliminar por el id*/
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar (@PathVariable Integer id) {
		detalleMenuService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
