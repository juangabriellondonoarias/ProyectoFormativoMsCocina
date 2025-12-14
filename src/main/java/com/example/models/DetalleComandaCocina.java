package com.example.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "DETALLE_COMANDA_COCINA")
public class DetalleComandaCocina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_comanda_cocina")
    private Integer idDetalleComandaCocina;

    /* Relación con la Comanda */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_comanda_cocina", nullable = false)
    private ComandaCocina comandaCocina;
    
    /* Relación con el Menú */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_menu", nullable = false)
    private Menu menu;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "notas", length = 200) // Nota: Si las notas son por plato, se mantiene aquí. Si son generales, deben ir solo en ComandaCocina.
    private String notas; 
    
    /* Constructor */
    public DetalleComandaCocina() {
    }

    /* Getters y Setters */

    public Integer getIdDetalleComandaCocina() {
        return idDetalleComandaCocina;
    }

    public void setIdDetalleComandaCocina(Integer idDetalleComandaCocina) {
        this.idDetalleComandaCocina = idDetalleComandaCocina;
    }

    public ComandaCocina getComandaCocina() {
        return comandaCocina;
    }

    public void setComandaCocina(ComandaCocina comandaCocina) {
        this.comandaCocina = comandaCocina;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
}