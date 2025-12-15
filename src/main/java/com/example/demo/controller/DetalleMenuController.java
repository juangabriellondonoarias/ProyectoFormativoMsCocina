package com.example.demo.controller;

import java.util.List; 
import java.util.Map;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


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

import com.example.demo.models.DetalleMenu;
import com.example.demo.service.impl.DetalleMenuService;


@RestController
@RequestMapping("api/detalle-menu")
@Tag(name = "Detalles de Menú", description = "Gestión de componentes y relación con las recetas de los platos del menú.")
public class DetalleMenuController {
	
	@Autowired
	private DetalleMenuService detalleMenuService;
	
    @Operation(summary = "Crear nuevo detalle de menú", description = "Añade un componente o relación de receta a un plato del menú.")
	@PostMapping
	public ResponseEntity<DetalleMenu>crear(@RequestBody DetalleMenu detalle){
		DetalleMenu nuevoDetalle = detalleMenuService.save(detalle);
		return ResponseEntity.ok(nuevoDetalle);
	}
	
    @Operation(summary = "Obtener todos los detalles de menú", description = "Retorna una lista de todos los componentes de los platos.")
	@GetMapping
	public List<DetalleMenu>obtener(){
		return detalleMenuService.findAll();
	}
	
    @Operation(summary = "Obtener detalle por ID", description = "Retorna el detalle de un plato específico usando su ID.")
	@GetMapping("/{id}")
	public ResponseEntity<DetalleMenu> obtener(@PathVariable Integer id){
		DetalleMenu detalle = detalleMenuService.findById(id);
		if(detalle != null) {
			return ResponseEntity.ok(detalle);
		}
		return ResponseEntity.notFound().build();
	}
	
    @Operation(summary = "Eliminar detalle por ID", description = "Elimina un componente de un plato usando su ID.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id){
		detalleMenuService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
    
    /*actualizar imagen*/
    
    @Operation(summary = "Actualización parcial de Imagen ", description = "Actualiza solo la URL de la imagen de un detalle de menú. Espera un JSON: {\"imagenUrl\": \"nueva_url_aqui\"}.")
    @RequestMapping(value = "/{id}/imagen", method = RequestMethod.PATCH)
    public ResponseEntity<DetalleMenu> actualizarImagen(@PathVariable Integer id, @RequestBody Map<String, String> body) {
        String nuevaUrl = body.get("imagenUrl");
        
        if (!body.containsKey("imagenUrl")) {
            return ResponseEntity.badRequest().build();
        }
        
        DetalleMenu detalleActualizado = detalleMenuService.actualizarImagenUrl(id, nuevaUrl);

        if (detalleActualizado != null) {
            return ResponseEntity.ok(detalleActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}