package com.enrutaglp.model;

import java.util.List;

public class Mapa {
	private int dimX;
	private int dimY;
	private List<Bloqueo> bloqueos;
	private List<Planta> plantas;
	
	
	public Mapa(int dimX, int dimY) {
		this.dimX = dimX; 
		this.dimY = dimY;
	}
	
	public int getDimX() {
		return dimX;
	}
	public void setDimX(int dimX) {
		this.dimX = dimX;
	}
	public int getDimY() {
		return dimY;
	}
	public void setDimY(int dimY) {
		this.dimY = dimY;
	}
}
