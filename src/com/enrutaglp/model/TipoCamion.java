package com.enrutaglp.model;

public class TipoCamion {
	private String tara; 
	private double pesoBruto; 
	private double capacidadGLP; 
	private double pesoGLP; 
	private double pesoCombinado; 
	private double capacidadTanque; 
	private double velocidadPromedio; 
	private int unidades;
	
	public TipoCamion(String tara, double pesoBruto, double capacidadGLP, double pesoGLP, double capacidadTanque,
			double velocidadPromedio, int unidades) {
		this.tara = tara;
		this.pesoBruto = pesoBruto;
		this.capacidadGLP = capacidadGLP;
		this.pesoGLP = pesoGLP;
		this.capacidadTanque = capacidadTanque;
		this.velocidadPromedio = velocidadPromedio;
		this.unidades = unidades;
		this.pesoCombinado = pesoBruto + pesoGLP;
	}

	public String getTara() {
		return tara;
	}

	public void setTara(String tara) {
		this.tara = tara;
	}

	public double getPesoBruto() {
		return pesoBruto;
	}

	public void setPesoBruto(double pesoBruto) {
		this.pesoBruto = pesoBruto;
	}

	public double getCapacidadGLP() {
		return capacidadGLP;
	}

	public void setCapacidadGLP(double capacidadGLP) {
		this.capacidadGLP = capacidadGLP;
	}

	public double getPesoGLP() {
		return pesoGLP;
	}

	public void setPesoGLP(double pesoGLP) {
		this.pesoGLP = pesoGLP;
	}

	public double getPesoCombinado() {
		return pesoCombinado;
	}

	public void setPesoCombinado(double pesoCombinado) {
		this.pesoCombinado = pesoCombinado;
	}

	public double getCapacidadTanque() {
		return capacidadTanque;
	}

	public void setCapacidadTanque(double capacidadTanque) {
		this.capacidadTanque = capacidadTanque;
	}

	public double getVelocidadPromedio() {
		return velocidadPromedio;
	}

	public void setVelocidadPromedio(double velocidadPromedio) {
		this.velocidadPromedio = velocidadPromedio;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	
	
	
}
