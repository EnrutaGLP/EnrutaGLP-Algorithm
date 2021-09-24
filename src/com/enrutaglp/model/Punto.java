package com.enrutaglp.model;

public class Punto {
	private int ubicacionX;
	private int ubicacionY;

	public Punto(int ubicacionX, int ubicacionY) {
		this.ubicacionX = ubicacionX;
		this.ubicacionY = ubicacionY;
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
}
