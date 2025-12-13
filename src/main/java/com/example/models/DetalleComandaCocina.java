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
	@Column(name = "id_detalle_cocina")
	private Integer idDetalleCocina;
	
	
	/*relacion con la comanda */
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_comanda_cocina" , nullable = false)
	private ComandaCocina comandaCocina;
	
	/*relacion con la receta pero por ahora estara comentada*/
	
	// @ManyToOne(fetch = FetchType.EAGER)
    // @JoinColumn(name = "id_receta", nullable = false)
    // private Receta receta;
	
	/*Campo auxiliar para la FK id_receta (Mientras la entidad Receta está comentada)*/
	@Column(name = "id_receta" , nullable = false)
	private Integer idReceta;
	
	@Column(name = "cantidad", nullable = false)
	private Integer cantidad;
	
	
	
	/*mapeo del estado del plato*/
	
	@Column(name = "estado_plato")
	private String estadoPlato = "EN ESPERA";
	
	/*constructor*/
	
	public DetalleComandaCocina() {
       
    }
	
	/*get y set*/
	
	public Integer getIdDetalleCocina() {
		return idDetalleCocina;
	}
	
	public void setIdDetalleCocina(Integer idDetalleCocina) {
		this.idDetalleCocina = idDetalleCocina;
	}
	
	public ComandaCocina getComandaCocina() {
		return comandaCocina;
	}
	
	public void setComandaCocina(ComandaCocina comandaCocina) {
		this.comandaCocina = comandaCocina;
	}
	
	public Integer getIdReceta() {
		return idReceta;
	}
	
	public void setIdReceta(Integer idReceta) {
		this.idReceta = idReceta;
	}
	
	public Integer getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
	public String getEstadoPlato() {
		return estadoPlato;
	}
	
	public void setEstadoPlato(String estadoPlato) {
		this.estadoPlato = estadoPlato;
	}

}
