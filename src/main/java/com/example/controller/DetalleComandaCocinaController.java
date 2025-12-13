package com.example.controller;

import java.util.List; 
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.models.DetalleComandaCocina;
import com.example.service.DetalleComandaCocinaService;


/*import de swagger*/
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/detalle-comanda-cocina")
@Tag(name = "Detalles de Comandas", description = "Gestión de los ítems individuales dentro de una comanda de cocina.")
public class DetalleComandaCocinaController {

	@Autowired
	private DetalleComandaCocinaService detalleComandaCocinaService;
	
	/*crear uno nuevo*/
	@Operation(summary = "Crear nuevo detalle de comanda", description = "Añade un nuevo ítem a una comanda de cocina específica.")
	@PostMapping
	public ResponseEntity<DetalleComandaCocina>crear(@RequestBody DetalleComandaCocina detalle){
		DetalleComandaCocina nuevoDetalle = detalleComandaCocinaService.save(detalle);
		return ResponseEntity.ok(nuevoDetalle);
	}
	
	/*obtener todo*/
	@Operation(summary = "Obtener todos los detalles de comandas", description = "Retorna una lista de todos los ítems de las comandas.")
	@GetMapping
	public List<DetalleComandaCocina>obtener(){
		return detalleComandaCocinaService.findAll();
	}
	
	 /*obtener por el id*/
	@Operation(summary = "Obtener detalle por ID", description = "Retorna un ítem de comanda específico usando su ID.")
	@GetMapping("/{id}")
	public ResponseEntity<DetalleComandaCocina> obtener(@PathVariable Integer id){
		DetalleComandaCocina detalle = detalleComandaCocinaService.findById(id);
		if(detalle != null) {
			return ResponseEntity.ok(detalle);
		}
		return ResponseEntity.notFound().build();
	}
	
	/*Eliminar por el id*/
	@Operation(summary = "Eliminar detalle por ID", description = "Elimina un ítem específico de una comanda usando su ID.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id){
		detalleComandaCocinaService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	@Operation(summary = "Actualización parcial de Cantidad (PATCH)", description = "Actualiza solo la 'cantidad' ")
	@RequestMapping(value = "/{id}/cantidad", method = RequestMethod.PATCH)
	public ResponseEntity<DetalleComandaCocina> actualizarCantidad(@PathVariable Integer id, @RequestBody Map<String, Integer> body) {
	    // Intentamos obtener la nueva cantidad del JSON {"cantidad": 3}
	    Integer cantidad = body.get("cantidad");
	    
	    if (cantidad == null || cantidad < 1) { 
	        return ResponseEntity.badRequest().body(null);
	    }
	    
	    // Llamada al servicio (que definiremos en el siguiente paso)
	    DetalleComandaCocina detalleActualizado = detalleComandaCocinaService.actualizarCantidad(id, cantidad);

	    if (detalleActualizado != null) {
	        return ResponseEntity.ok(detalleActualizado);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	
	
}
