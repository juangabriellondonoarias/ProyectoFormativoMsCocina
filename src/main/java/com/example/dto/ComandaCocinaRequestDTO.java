package com.example.dto;

import com.example.models.EstadoComanda;
import com.example.models.PrioridadComanda;

public class ComandaCocinaRequestDTO {
    
    private Integer idComandaRestaurante;
    private Integer idMesa;
    
    private EstadoComanda estado; 
    private PrioridadComanda prioridad;
    
    private String notas;

    /* Constructor */
    public ComandaCocinaRequestDTO() {
    }

    /* Getters y Setters */
    
    public Integer getIdComandaRestaurante() {
        return idComandaRestaurante;
    }

    public void setIdComandaRestaurante(Integer idComandaRestaurante) {
        this.idComandaRestaurante = idComandaRestaurante;
    }

    public Integer getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(Integer idMesa) {
        this.idMesa = idMesa;
    }

    public EstadoComanda getEstado() {
        return estado;
    }

    public void setEstado(EstadoComanda estado) {
        this.estado = estado;
    }

    public PrioridadComanda getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(PrioridadComanda prioridad) {
        this.prioridad = prioridad;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
}
