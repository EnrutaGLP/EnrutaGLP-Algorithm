package com.enrutaglp.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Camion {

	private String codigo; 
	private String placa;
	private int ubicacionActualX;
	private int ubicacionActualY;
	private double cargaActualGLP; 
	private double cargaActualPetroleo; 
	private byte estado; 
	private TipoCamion tipo;
	private List<EntregaPedido>entregas;
	private List<Punto>ruta;
	
	public Camion(String codigo, int ubicacionActualX,int ubicacionActualY, double cargaActualGLP, double cargaActualPetroleo) {
		this.codigo = codigo;
		this.ubicacionActualX = ubicacionActualX;
		this.ubicacionActualY = ubicacionActualY;
		this.cargaActualGLP = cargaActualGLP;
		this.cargaActualPetroleo = cargaActualPetroleo;
		this.entregas = new ArrayList<EntregaPedido>();
		this.ruta = new ArrayList<Punto>();
	}

	public Camion(String codigo, int ubicacionActualX,int ubicacionActualY, double cargaActualGLP, double cargaActualPetroleo,
			TipoCamion tipo) {
		this.codigo = codigo;
		this.ubicacionActualX = ubicacionActualX;
		this.ubicacionActualY = ubicacionActualY;
		this.cargaActualGLP = cargaActualGLP;
		this.cargaActualPetroleo = cargaActualPetroleo;
		this.entregas = new ArrayList<EntregaPedido>();
		this.ruta = new ArrayList<Punto>();
		this.tipo = tipo;
	}
	
	public EntregaPedido addPedido(LocalDateTime horaSalida, Pedido pedido) {
		//Verificar 
		int distancia, tiempo;
		LocalDateTime horaLlegada;
		double cantidadEntregada = 0.0;
		EntregaPedido entregaPedido = null; 
		if(entregas.isEmpty()) {
			ubicacionActualX = 0; 
			ubicacionActualY = 0;
			cargaActualGLP	= tipo.getCapacidadGLP();
			distancia = Math.abs(pedido.getUbicacionX()-ubicacionActualX) + Math.abs(pedido.getUbicacionY()-ubicacionActualY);
			tiempo = distancia/(int)tipo.getVelocidadPromedio();
			horaLlegada = horaSalida.plusHours(tiempo);
			cantidadEntregada = (pedido.getCantidadGLP() > cargaActualGLP)? pedido.getCantidadGLP() : cargaActualGLP;
			entregaPedido = new EntregaPedido(cantidadEntregada, horaLlegada, horaSalida, 
					calcularConsumoPetroleo(distancia,tipo.getPesoCombinado()), this, pedido);
			this.entregas.add(entregaPedido);
		}
		return entregaPedido;
	}
	
	public double calcularConsumoPetroleo(int distancia,double peso) {
		return distancia*peso/150;
	}
	
	public String getCodigo() {
		return codigo;
	}



	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}



	public String getPlaca() {
		return placa;
	}



	public void setPlaca(String placa) {
		this.placa = placa;
	}



	public int getUbicacionActualX() {
		return ubicacionActualX;
	}



	public void setUbicacionActualX(int ubicacionActualX) {
		this.ubicacionActualX = ubicacionActualX;
	}



	public int getUbicacionActualY() {
		return ubicacionActualY;
	}



	public void setUbicacionActualY(int ubicacionActualY) {
		this.ubicacionActualY = ubicacionActualY;
	}



	public double getCargaActualGLP() {
		return cargaActualGLP;
	}



	public void setCargaActualGLP(double cargaActualGLP) {
		this.cargaActualGLP = cargaActualGLP;
	}



	public double getCargaActualPetroleo() {
		return cargaActualPetroleo;
	}



	public void setCargaActualPetroleo(double cargaActualPetroleo) {
		this.cargaActualPetroleo = cargaActualPetroleo;
	}



	public byte getEstado() {
		return estado;
	}



	public void setEstado(byte estado) {
		this.estado = estado;
	}



	public TipoCamion getTipo() {
		return tipo;
	}



	public void setTipo(TipoCamion tipo) {
		this.tipo = tipo;
	}
	
	
}
