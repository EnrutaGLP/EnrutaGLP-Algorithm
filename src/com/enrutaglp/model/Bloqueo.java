package com.enrutaglp.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Bloqueo {
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	private List<Punto> puntos;
	private byte tipoBloqueo;

	public Bloqueo(LocalDateTime fechaInicio, LocalDateTime fechaFin,byte tipoBloqueo) {
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.tipoBloqueo = tipoBloqueo;
		this.puntos = new ArrayList<Punto>();
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

	public List<Punto> getPuntos() {
		return puntos;
	}

	public void setPuntos(List<Punto> puntos) {
		this.puntos = puntos;
	}

	public byte getTipoBloqueo() {
		return tipoBloqueo;
	}

	public void setTipoBloqueo(byte tipoBloqueo) {
		this.tipoBloqueo = tipoBloqueo;
	}

}
