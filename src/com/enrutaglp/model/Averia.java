package com.enrutaglp.model;

import java.time.LocalDateTime;

public class Averia {
	private LocalDateTime fecha;
	private Camion camion;
	
	public Averia() {
		
	}
	
	public Averia(LocalDateTime fecha, Camion camion) {
		this.fecha = fecha;
		this.camion = camion;
	}
	
	public LocalDateTime getFecha() {
		return fecha;
	}
	
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	
	public Camion getCamion() {
		return camion;
	}
	
	public void setCamion(Camion camion) {
		this.camion = camion;
	}
	
}
