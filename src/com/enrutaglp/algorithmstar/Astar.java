package com.enrutaglp.algorithmstar;

import java.util.ArrayList;
import java.util.Map;
import java.util.Collections;

import com.enrutaglp.model.Camion;
import com.enrutaglp.model.Pedido;


public class Astar { 
	Map<String,Pedido>pedidos; 
	Map<String,Camion>flota;
	Mapa mapa;
	Camino caminoMasCorto;
	ArrayList<Nodo> listaCerrada;
	
		
	public Astar(int mapX, int mapY,  Map<String,Pedido>pedidos, Map<String,Camion>flota) {
		this.mapa= new Mapa(mapX,mapY);
		this.pedidos = pedidos; 
		this.flota = flota; 
	}	
	public void run() {
		
	}
	
	public float calcularHeuristica(int iniX, int iniY, int finX, int finY) {
		float difX=finX-iniX;
		float difY=finY-iniY;
		return (float)Math.sqrt((difX*difX)+(difY*difY));
	}
	private class ListaNodosOrdenadas {
		private ArrayList<Nodo> lista = new ArrayList<Nodo>();

		public Nodo getPrimero() {
			return lista.get(0);
		}
		public void clear() {
			lista.clear();
		}
		public void anadir(Nodo node) {
			lista.add(node);
			//Collections.sort(lista);
		}
		public void remover(Nodo n) {
			lista.remove(n);
		}
		public int tamano() {
			return lista.size();
		}
		public boolean contiene(Nodo n) {
			return lista.contains(n);
		}
	}
}