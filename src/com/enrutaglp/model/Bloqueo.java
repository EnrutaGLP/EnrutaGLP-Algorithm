package com.enrutaglp.model;

import java.time.LocalDateTime;

public class Bloqueo {
	private LocalDateTime fechaInicio; 
	private LocalDateTime fechaFin;
	
	public Bloqueo(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDateTime getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDateTime fechaFin) {
		this.fechaFin = fechaFin;
	} 
	
}
