package com.enrutaglp.algorithmstar;

import java.util.List;
import java.util.Map;

import com.enrutaglp.model.Camion;
import com.enrutaglp.model.Pedido;

public class Astar { 
	Map<String,Pedido>pedidos; 
	Map<String,Camion>flota; 
	int mapX; 
	int mapY;
	
	
		
	public Astar(int mapX, int mapY, Map<String,Pedido>pedidos, Map<String,Camion>flota) {
		this.mapX = mapX; 
		this.mapY = mapY; 
		this.pedidos = pedidos; 
		this.flota = flota; 
	}
	
	public void run(int a, int b) {
		
	}
	
	public float calcularHeuristica(int iniX, int iniY, int finX, int finY) {
		float difX=finX-iniX;
		float difY=finY-iniY;
		return (float)Math.sqrt((difX*difX)+(difY*difY));
	}
}