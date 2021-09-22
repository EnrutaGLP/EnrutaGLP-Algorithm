package com.enrutaglp.model;

public class Camion {

	private String codigo; 
	private String placa;
	private int ubicacionActualX;
	private int ubicacionActualY;
	private double cargaActualGLP; 
	private double cargaActualPetroleo; 
	private byte estado; 
	private TipoCamion tipo;
	
	
	
	public Camion(String codigo, int ubicacionActualX,int ubicacionActualY, double cargaActualGLP, double cargaActualPetroleo) {
		this.codigo = codigo;
		this.ubicacionActualX = ubicacionActualX;
		this.ubicacionActualY = ubicacionActualY;
		this.cargaActualGLP = cargaActualGLP;
		this.cargaActualPetroleo = cargaActualPetroleo;
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
