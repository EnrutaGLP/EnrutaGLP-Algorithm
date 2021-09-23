package com.enrutaglp.algorithmstar;

import java.util.ArrayList;

public class Camino{
	private ArrayList<Nodo> coordenadasCamino=new ArrayList<Nodo>();
	
	public Camino() {
		
	}
	public int getTamano() {
		return coordenadasCamino.size();
	}
	public Nodo getCoordenadasCamino(int i) {
		return coordenadasCamino.get(i);
	}
	public int getX(int i) {
		return getCoordenadasCamino(i).getX();
	}
	public int getY(int i) {
		return getCoordenadasCamino(i).getY();
	}
	public void anadirCoordenadas(Nodo nod) {
		coordenadasCamino.add(nod);
	}
	public void anadirCoordenadasInicio(Nodo nod) {
		coordenadasCamino.add(0,nod);
	}
	public boolean contiene(int x, int y) {
		for (Nodo nod: coordenadasCamino) {
			if(nod.getX()==x & nod.getY()==y) {
				return true;
			}
		}
		return false;
	}
}