package com.enrutaglp.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pedido {
	private String codigo; 
	private String cliente;
	private double cantidadGLP;
	private int ubicacionX;
	private int ubicacionY;
	private LocalDateTime fechaHoraPedido;
	private LocalDateTime fechaHoraLimite;
	private LocalDateTime fechaHoraCompletado;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	private byte estado;
	
	public Pedido(String codigo, String cliente, double cantidadGLP, int ubicacionX, int ubicacionY, String fechaLimite, String horaLimite) {
		this.codigo = codigo; 
		this.cliente = cliente;
		this.cantidadGLP = cantidadGLP;
		this.ubicacionX = ubicacionX;
		this.ubicacionY = ubicacionY;
		this.fechaHoraLimite = LocalDateTime.parse(fechaLimite + " " + horaLimite,formatter);
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public double getCantidadGLP() {
		return cantidadGLP;
	}

	public void setCantidadGLP(double cantidadGLP) {
		this.cantidadGLP = cantidadGLP;
	}

	public int getUbicacionX() {
		return ubicacionX;
	}

	public void setUbicacionX(int ubicacionX) {
		this.ubicacionX = ubicacionX;
	}

	public int getUbicacionY() {
		return ubicacionY;
	}

	public void setUbicacionY(int ubicacionY) {
		this.ubicacionY = ubicacionY;
	}

	public LocalDateTime getFechaHoraPedido() {
		return fechaHoraPedido;
	}

	public void setFechaHoraPedido(LocalDateTime fechaHoraPedido) {
		this.fechaHoraPedido = fechaHoraPedido;
	}

	public LocalDateTime getFechaHoraLimite() {
		return fechaHoraLimite;
	}

	public void setFechaHoraLimite(LocalDateTime fechaHoraLimite) {
		this.fechaHoraLimite = fechaHoraLimite;
	}

	public LocalDateTime getFechaHoraCompletado() {
		return fechaHoraCompletado;
	}

	public void setFechaHoraCompletado(LocalDateTime fechaHoraCompletado) {
		this.fechaHoraCompletado = fechaHoraCompletado;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public byte getEstado() {
		return estado;
	}

	public void setEstado(byte estado) {
		this.estado = estado;
	}
	
	

}
