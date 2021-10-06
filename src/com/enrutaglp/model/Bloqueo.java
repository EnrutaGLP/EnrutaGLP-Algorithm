package com.enrutaglp.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Bloqueo {
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	private List<Punto> puntos;
	private byte tipoBloqueo;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

	public Bloqueo(String fechaInicio, String horaInicio, String fechaFin, String horaFin, 
		List<Punto> puntos,byte tipoBloqueo) {
		this.fechaInicio = LocalDateTime.parse(fechaInicio + " " + horaInicio,formatter);
		this.fechaFin = LocalDateTime.parse(fechaFin + " " + horaFin,formatter);
		this.puntos = new ArrayList<Punto>(); 
		this.tipoBloqueo = tipoBloqueo;
	}
	
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
