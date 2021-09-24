package com.enrutaglp.model;

import java.time.LocalDateTime;

public class EntregaPedido {
	private double cantidadEntregada; 
	private LocalDateTime horaEntregada; 
	private LocalDateTime horaSalida; 
	private double consumoPetroleo;
	private Camion camion; 
	private Pedido pedido; 
	
	
	
	public EntregaPedido(double cantidadEntregada, LocalDateTime horaEntregada, LocalDateTime horaSalida,
			double consumoPetroleo, Camion camion, Pedido pedido) {
		this.cantidadEntregada = cantidadEntregada;
		this.horaEntregada = horaEntregada;
		this.horaSalida = horaSalida;
		this.consumoPetroleo = consumoPetroleo;
		this.camion = camion;
		this.pedido = pedido;
	}
	
	
	public EntregaPedido(double cantidadEntregada, LocalDateTime horaEntregada, LocalDateTime horaSalida,
			double consumoPetroleo) {
		this.cantidadEntregada = cantidadEntregada;
		this.horaEntregada = horaEntregada;
		this.horaSalida = horaSalida;
		this.consumoPetroleo = consumoPetroleo;
	}



	public Camion getCamion() {
		return this.camion;
	}
	public void setCamion(Camion camion) {
		this.camion = camion;
	}
	public Pedido getPedido() {
		return this.pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
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
