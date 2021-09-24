package com.enrutaglp.model;

public class Planta {
	private int ubicacionX;
	private int ubicacionY;
	private double capacidadPetroleo;
	private double capacidadGLP;
	private double cargaActualPetroleo;
	private double cargaActualGLP;
	private boolean esPrincipal;
	
	
	
	public Planta(int ubicacionX, int ubicacionY, double capacidadPetroleo, double capacidadGLP,
					double cargaActualPetroleo, double cargaActualGLP, boolean esPrincipal) {
		this.ubicacionX = ubicacionX;
		this.ubicacionY = ubicacionY;
		this.capacidadPetroleo = capacidadPetroleo;
		this.capacidadGLP = capacidadGLP;
		this.cargaActualPetroleo = cargaActualPetroleo;
		this.cargaActualGLP = cargaActualGLP;
		this.esPrincipal = esPrincipal;
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



	public double getCapacidadPetroleo() {
		return capacidadPetroleo;
	}



	public void setCapacidadPetroleo(double capacidadPetroleo) {
		this.capacidadPetroleo = capacidadPetroleo;
	}



	public double getCapacidadGLP() {
		return capacidadGLP;
	}



	public void setCapacidadGLP(double capacidadGLP) {
		this.capacidadGLP = capacidadGLP;
	}



	public double getCargaActualPetroleo() {
		return cargaActualPetroleo;
	}



	public void setCargaActualPetroleo(double cargaActualPetroleo) {
		this.cargaActualPetroleo = cargaActualPetroleo;
	}



	public double getCargaActualGLP() {
		return cargaActualGLP;
	}



	public void setCargaActualGLP(double cargaActualGLP) {
		this.cargaActualGLP = cargaActualGLP;
	}



	public boolean isEsPrincipal() {
		return esPrincipal;
	}



	public void setEsPrincipal(boolean esPrincipal) {
		this.esPrincipal = esPrincipal;
	}
	
	
}
