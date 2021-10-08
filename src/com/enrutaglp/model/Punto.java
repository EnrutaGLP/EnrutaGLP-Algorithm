package com.enrutaglp.model;

public class Punto {
	private int ubicacionX;
	private int ubicacionY;
	private int orden; 
	private String codigoPedido;
	private boolean planta;
	
	
	
	public int getOrden() {
		return orden;
	}

	public Punto clone() {
		Punto p = new Punto(); 
		p.ubicacionX = this.ubicacionX; 
		p.ubicacionY = this.ubicacionY; 
		p.orden = this.orden; 
		p.planta = this.planta; 
		p.codigoPedido = this.codigoPedido;
		return p; 
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

	public Punto() {
		codigoPedido = null;
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
		this.codigoPedido = null;
	}

	public Punto(int ubicacionX, int ubicacionY, int orden, String codigoPedido) {
		this.ubicacionX = ubicacionX;
		this.ubicacionY = ubicacionY;
		this.orden = orden;
		
		if((this.ubicacionX==0)&(this.ubicacionY==0)) {
			this.setPlanta(true);
		}
		else {
			this.setPlanta(false);
			this.codigoPedido = codigoPedido; 
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

	public String getCodigoPedido() {
		return codigoPedido;
	}

	public void setCodigoPedido(String codigoPedido) {
		this.codigoPedido = codigoPedido;
	}
	
	
}
