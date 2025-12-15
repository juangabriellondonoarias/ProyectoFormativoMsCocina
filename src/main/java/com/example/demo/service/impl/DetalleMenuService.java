package com.example.demo.service.impl;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.DetalleMenu;
import com.example.demo.repository.DetalleMenuRepository;


@Service
public class DetalleMenuService {

	@Autowired
	private DetalleMenuRepository detalleMenuRepository;
	
	/*guardar o editar */
	
	public DetalleMenu save(DetalleMenu detalleMenu) {
		return detalleMenuRepository.save(detalleMenu);
		
	}
	
	/*obtener detalles*/
	@Transactional(readOnly = true)
	public List<DetalleMenu> findAll(){
		return detalleMenuRepository.findAll();
	}
	
	/*obtener por el id*/
	@Transactional(readOnly = true)
	public DetalleMenu findById(Integer id) {
        return detalleMenuRepository.findById(id).orElse(null);
    }
	
	/*eliminar por el id*/
	
	public void deleteById(Integer id) {
		detalleMenuRepository.deleteById(id);
	}
	
	
	
	
	@Transactional
    public DetalleMenu actualizarImagenUrl(Integer id, String nuevaUrl) {
        return detalleMenuRepository.findById(id).map(detalle -> {
            detalle.setImagenUrl(nuevaUrl);
            return detalleMenuRepository.save(detalle);
        }).orElse(null);
    }
	
}
