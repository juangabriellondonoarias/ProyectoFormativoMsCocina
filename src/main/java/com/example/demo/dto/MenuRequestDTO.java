package com.example.demo.dto;


public class MenuRequestDTO {
    
    private String nombre;
    private Double precio_unitario;
    
    // El campo 'activo' se puede incluir si deseas forzar su valor en el POST/PUT
    private Boolean activo; 

    /* Constructor */
    public MenuRequestDTO() {
    }

    /* Getters y Setters */

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(Double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}