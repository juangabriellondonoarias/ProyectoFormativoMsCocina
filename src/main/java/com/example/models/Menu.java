package com.example.models;

import jakarta.persistence.Column; 
import jakarta.persistence.Entity;  
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity

/*@Table  nos permite tambien crear la tabla con el nombre que deseamos 
 * ej: @Table(name= "tbl_menu")*/

@Table(name= "MENU_2")

public class Menu {
	
	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_menu")
	private Integer idmenu;

	@Column(unique = true)
	private String  nombre;
	
	private Integer precio_unitario;
	
	private boolean activo = true ;
	
	//constructor vacio
	public Menu() {
        
    }
	
	// get y set


	public void setIdmenu(Integer idmenu) {
        this.idmenu = idmenu;
    }
	
    public void setPrecio_unitario(Integer precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

   
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    
    public Integer getIdmenu() {
        return idmenu;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getPrecio_unitario() {
        return precio_unitario;
    }
    
    public boolean isActivo() {
        return activo;
    }
	
}

