package com.example.demo.service.impl;
import com.example.demo.dto.RecetaDTO;
import com.example.demo.mappers.RecetaMapper;
import com.example.demo.models.Receta;
import com.example.demo.repository.IngredienteRepository;
import com.example.demo.repository.RecetaRepository;
import com.example.demo.service.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RecetaServiceImpl implements RecetaService {

    @Autowired
    private RecetaRepository repositorio;
    
    @Transactional // Si algo falla al guardar pasos o ingredientes, se revierte todo
    public RecetaDTO guardarCompleta(RecetaDTO dto) {
        // Aseguramos que sea una creación nueva
        dto.setIdReceta(null);
        
        // Convertimos el DTO gigante a un grafo de entidades conectadas
        Receta recetaEntity = RecetaMapper.toEntity(dto);
        
        // ¡MAGIA! Al guardar la receta, Hibernate guarda automáticamente
        // los pasos y los ingredientes asociados gracias al CascadeType.ALL
        Receta recetaGuardada = repositorio.save(recetaEntity);
        
        // Convertimos de vuelta a DTO (necesitarás implementar el toDTO completo en el mapper)
        return RecetaMapper.toDTO(recetaGuardada);
    }

    @Override
    public List<RecetaDTO> listarTodas() {
        return repositorio.findAll().stream()
                .map(RecetaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RecetaDTO obtenerPorId(Integer id) {
        Receta receta = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada con ID: " + id));
        return RecetaMapper.toDTO(receta);
    }

    @Override
    public RecetaDTO guardar(RecetaDTO dto) {
        // Validaciones básicas de FK
        if(dto.getIdCategoria() == null) {
            throw new RuntimeException("La receta debe tener una Categoría y un Módulo asignados.");
        }
        
        Receta receta = RecetaMapper.toEntity(dto);
        return RecetaMapper.toDTO(repositorio.save(receta));
    }

    @Override
    public RecetaDTO actualizar(Integer id, RecetaDTO dto) {
        Receta recetaExistente = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada con ID: " + id));

        // Actualizamos datos básicos
        recetaExistente.setNombreReceta(dto.getNombreReceta());
        recetaExistente.setTiempoPreparacion(dto.getTiempoPreparacion());
        recetaExistente.setPorciones(dto.getPorciones());
        recetaExistente.setTemperatura(dto.getTemperatura());
        recetaExistente.setNotasAdicionales(dto.getNotasAdicionales());

        // Actualizamos relaciones si cambiaron
        // Usamos el Mapper para generar las entidades con ID desde el DTO
        Receta temp = RecetaMapper.toEntity(dto);
        recetaExistente.setCategoria(temp.getCategoria());

        return RecetaMapper.toDTO(repositorio.save(recetaExistente));
    }
    
    @Override
    public RecetaDTO actualizarParcial(Integer id, RecetaDTO dto) {
        // 1. Buscar la receta existente
        Receta receta = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada con ID: " + id));

        // 2. Actualizar SOLO los campos que traigan valor (no nulos)
        
        if (dto.getNombreReceta() != null) {
            receta.setNombreReceta(dto.getNombreReceta());
        }
        
        if (dto.getTiempoPreparacion() != null) {
            receta.setTiempoPreparacion(dto.getTiempoPreparacion());
        }
        
        if (dto.getPorciones() != null) {
            receta.setPorciones(dto.getPorciones());
        }
        
        if (dto.getTemperatura() != null) {
            receta.setTemperatura(dto.getTemperatura());
        }
        
        if (dto.getNotasAdicionales() != null) {
            receta.setNotasAdicionales(dto.getNotasAdicionales());
        }

        Receta actualizada = repositorio.save(receta);
        
        return RecetaMapper.toDTO(actualizada);
    }

    @Override
    public void eliminar(Integer id) {
        if (!repositorio.existsById(id)) {
            throw new RuntimeException("Receta no encontrada con ID: " + id);
        }
        repositorio.deleteById(id);
    }
}