package com.enrutaglp.model;

public class Punto {
	private int ubicacionX;
	private int ubicacionY;
	private int orden; 
	private boolean planta;
	
	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public boolean isPlanta() {
		return planta;
	}

	public void setPlanta(boolean planta) {
		this.planta = planta;
	}

	public Punto(int ubicacionX, int ubicacionY, int orden) {
		this.ubicacionX = ubicacionX;
		this.ubicacionY = ubicacionY;
		this.orden = orden;
		
		if((this.ubicacionX==0)&(this.ubicacionY==0)) {
			this.setPlanta(true);
		}
		else {
			this.setPlanta(false);
		}
		
	}

	public int getUbicacionX() {
		return ubicacionX;
	}

	public void setUbicacionX(int ubicacionX) {
		this.ubicacionX = ubicacionX;
		
		if((this.ubicacionX==0)&(this.ubicacionY==0)) {
			this.setPlanta(true);
		}
		else {
			this.setPlanta(false);
		}
		
	}

	public int getUbicacionY() {
		return ubicacionY;
	}

	public void setUbicacionY(int ubicacionY) {
		this.ubicacionY = ubicacionY;
		
		if((this.ubicacionX==0)&(this.ubicacionY==0)) {
			this.setPlanta(true);
		}
		else {
			this.setPlanta(false);
		}
		
	}
}
