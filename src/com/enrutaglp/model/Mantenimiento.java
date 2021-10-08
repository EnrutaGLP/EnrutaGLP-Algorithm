package com.enrutaglp.model;

import java.time.LocalDateTime;

public class Mantenimiento {
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	private Camion camion;
	private byte tipoMantenimiento;

	public Mantenimiento() {
	}

	public Mantenimiento(LocalDateTime fechaInicio, LocalDateTime fechaFin, byte tipoMantenimiento,
			Camion camion) {
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.tipoMantenimiento = tipoMantenimiento;
		this.camion = camion;
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

	public byte getTipoMantenimiento() {
		return tipoMantenimiento;
	}

	public void setTipoMantenimiento(byte tipoMantenimiento) {
		this.tipoMantenimiento = tipoMantenimiento;
	}

	public Camion getCamion() {
		return camion;
	}

	public void setCamion(Camion camion) {
		this.camion = camion;
	}
	
	

}
