package com.example.models;

import java.time.LocalDateTime;   

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import com.example.models.EstadoComanda;     
import com.example.models.PrioridadComanda;

@Entity

@Table(name = "COMANDA_COCINA")
public class ComandaCocina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_comanda_cocina")
	private Integer idComandaCocina;
	
	@Column(name = "id_comanda_restaurante", nullable = false)
	private Integer idComandaRestaurante;
	
	@Column(name = "id_mesa" , nullable = false)
	private Integer idMesa;
	
	
	
	 /*mapeo del datetime*/
	
	@CreationTimestamp
	@Column(name = "hora_entrada" , nullable = false , updatable = false)
	private LocalDateTime horaEntrada;
	
	/*mapeo del enum estado*/
	/* Mapeo del ENUM ESTADO  */
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoComanda estado = EstadoComanda.EN_ESPERA; 
	
    /* Mapeo del enum prioridad */
	@Enumerated(EnumType.STRING)
	@Column(name = "prioridad" , nullable = false) 
	private PrioridadComanda prioridad = PrioridadComanda.MEDIA;
	
	
	@Column(name = "notas" , length = 200)
	private String notas;
	
	
	/*conatructor*/
	
	public ComandaCocina() {
		
	}
	
	/*get y set*/
	
	public Integer getIdComandaCocina() {
		return idComandaCocina;
	}
	
	public Integer getIdComandaRestaurante() {
		return idComandaRestaurante;
		
	}
	
	public Integer getIdMesa () { 
		return idMesa;
	}
	
	public LocalDateTime getHoraEntrada () {
		return horaEntrada;
	}
	
	public EstadoComanda getEstado () {
		return estado;
	}
	
	public PrioridadComanda getPrioridad () {
		return prioridad;
	}
	
	public void setIdComandaCocina (Integer idComandaCocina) {
		this.idComandaCocina = idComandaCocina;
	}
	
	public void setIdComandaRestaurante(Integer idComandaRestaurante) {
		this.idComandaRestaurante = idComandaRestaurante;
	}
	
	public void setIdMesa(Integer idMesa) {
		this.idMesa = idMesa;
	}
	
	public void setHoraEntrada(LocalDateTime horaEntrada ) {
		this.horaEntrada = horaEntrada;
	}
	
	public void setEstado( EstadoComanda estado) {
		this.estado = estado;
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
