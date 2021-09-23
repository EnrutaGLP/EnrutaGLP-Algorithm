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
	
	
	public Camion(String codigo, int ubicacionActualX,int ubicacionActualY, double cargaActualGLP, double cargaActualPetroleo) {
		this.codigo = codigo;
		this.ubicacionActualX = ubicacionActualX;
		this.ubicacionActualY = ubicacionActualY;
		this.cargaActualGLP = cargaActualGLP;
		this.cargaActualPetroleo = cargaActualPetroleo;
		this.entregas = new ArrayList<EntregaPedido>();
	}

	public Camion(String codigo, int ubicacionActualX,int ubicacionActualY, double cargaActualGLP, double cargaActualPetroleo,
			TipoCamion tipo) {
		this.codigo = codigo;
		this.ubicacionActualX = ubicacionActualX;
		this.ubicacionActualY = ubicacionActualY;
		this.cargaActualGLP = cargaActualGLP;
		this.cargaActualPetroleo = cargaActualPetroleo;
		this.entregas = new ArrayList<EntregaPedido>();
		this.tipo = tipo;
	}
	
	public boolean verificarDisponibilidad(LocalDateTime horaSalida, Pedido pedido) {
		//Verificar 
		if(entregas.isEmpty()) {
			
		}
		
		
		return true;
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
