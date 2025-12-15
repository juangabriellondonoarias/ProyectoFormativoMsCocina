package com.example.service;

import com.example.models.Menu;  
import com.example.repository.MenuRepository;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MenuService {

    // ... (Tu código de inyección y constructor) ...
	private final MenuRepository menuRepository;
	 
	public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }
	
	/* ... (findAll, findById, save, deleteById, update - Todos OK) ... */
    public List<Menu> findAll (){ /* ... */ return menuRepository.findAll(); }
    public Optional<Menu> findById (Integer idmenu){ /* ... */ return menuRepository.findById(idmenu); }
    public Menu save(Menu menu) { /* ... */ return menuRepository.save(menu); }
    public void deleteById(Integer idmenu) { /* ... */ menuRepository.deleteById(idmenu); }
    @Transactional
    public Menu update(Menu menu) { /* ... */ /* implementación PUT */ return menuRepository.save(menu); }
	
	
	@Transactional
	public Menu actualizarCampo(Integer id, String campo, Object valor) {
	    Menu menuExistente = menuRepository.findById(id).orElse(null);
	    
	    if (menuExistente != null) {
	        try {
	            // 1. Genera el nombre del setter (ej: "nombre" -> "setNombre")
	            String setterName = "set" + campo.substring(0, 1).toUpperCase() + campo.substring(1);
	            
	            // 2. Determinar la clase esperada y ajustar el valor si es necesario
	            Class<?> valueClass = valor.getClass();
	            Object valorAjustado = valor;
                
	            // Manejo de tipos numéricos: Si se espera Double pero se recibe Integer (desde JSON)
	            if (campo.equalsIgnoreCase("precio_unitario") && valor instanceof Integer) {
	                valueClass = Double.class;
	                valorAjustado = ((Integer) valor).doubleValue();
	            }
	            
	            // 3. Buscar el método setter en la clase Menu
	            Method setter = menuExistente.getClass().getMethod(setterName, valueClass);
	            
	            // 4. Invoca el setter en el objeto MenuExistente
	            setter.invoke(menuExistente, valorAjustado);
	            
	            return menuRepository.save(menuExistente);
	            
	        } catch (NoSuchMethodException e) {
	            System.err.println("Error en MenuService: Setter no encontrado para el campo o tipo " + campo + ". Detalles: " + e.getMessage());
	            return null;
	        } catch (Exception e) {
	            System.err.println("Error al actualizar campo: " + e.getMessage());
	            return null;
	        }
	    }
	    return null;
	}
	
	
}
