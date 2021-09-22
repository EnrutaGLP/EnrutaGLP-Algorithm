package com.enrutaglp.model;

import java.time.LocalDateTime;

public class EntregaPedido {
	private double cantidadEntregada; 
	private LocalDateTime horaEntregada; 
	private LocalDateTime horaSalida; 
	private double consumoPetroleo;
	private String codigoCamion; 
	private String codigoPedido; 
	
	public double getCantidadEntregada() {
		return cantidadEntregada;
	}
	public void setCantidadEntregada(double cantidadEntregada) {
		this.cantidadEntregada = cantidadEntregada;
	}
	public LocalDateTime getHoraEntregada() {
		return horaEntregada;
	}
	public void setHoraEntregada(LocalDateTime horaEntregada) {
		this.horaEntregada = horaEntregada;
	}
	public double getConsumoPetroleo() {
		return consumoPetroleo;
	}
	public void setConsumoPetroleo(double costo) {
		this.consumoPetroleo = costo;
	} 
	public LocalDateTime getHoraSalida() {
		return horaSalida;
	}
	public void setHoraSalida(LocalDateTime horaSalida) {
		this.horaSalida = horaSalida;
	}
}
