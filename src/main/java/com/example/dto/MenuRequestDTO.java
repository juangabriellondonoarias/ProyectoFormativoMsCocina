package com.example.dto;



 
public class MenuRequestDTO {
    
    // El cliente solo envía estos campos
    private String nombre;
    private Integer precio_unitario;
    
    
 // ⬅constructor (Necesario para Spring/Jackson)
    public MenuRequestDTO() {
        
    }

    // constructor
    public MenuRequestDTO(String nombre, Integer precio_unitario) {
        this.nombre = nombre;
        this.precio_unitario = precio_unitario;
    }

    // get y set

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(Integer precio_unitario) {
        this.precio_unitario = precio_unitario;
    }
   
}
