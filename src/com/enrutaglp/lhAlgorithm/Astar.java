package com.enrutaglp.lhAlgorithm;

import java.util.ArrayList;
import java.util.Map;
import java.util.Iterator;
import java.util.Collections;

import com.enrutaglp.model.Camion;
import com.enrutaglp.model.Pedido;
import java.time.LocalDateTime;

public class Astar { 
	Map<String,Pedido>pedidos; 
	Map<String,Camion>flota;
	Mapa mapa;
	Camino caminoMasCorto;
	ArrayList<Nodo> listaCerrada;
	ListaNodosOrdenadas listaAbierta;
	LocalDateTime fechaIniSimulacion;
		
	public Astar(int mapX, int mapY, Map<String,Pedido>pedidos, Map<String,Camion>flota) {
		this.mapa= new Mapa(mapX,mapY);
		this.pedidos = pedidos; 
		this.flota = flota;
		listaCerrada=new ArrayList<Nodo>();
		listaAbierta=new ListaNodosOrdenadas();
		caminoMasCorto= new Camino();
	}
	public void resolverPedidos() {
		
		int lenPedidos=this.pedidos.size();
		int tamCamino=0;
		ArrayList<Nodo> camionesEnMovimiento;
		camionesEnMovimiento=new ArrayList<Nodo>();
		Iterator<Map.Entry<String,Pedido>> entries = pedidos.entrySet().iterator();
		int lenCamMov=0;
		float heuristica=Float.MAX_VALUE;
		float heuristica2=0;
		float heuristicaDesdeIni=0;
		Nodo camionMasCerca=new Nodo(0,0);
		int tamCam=0;
		int j=-1;
		boolean usoCamionMov=false;
		while(entries.hasNext()) {
			Map.Entry<String,Pedido> entry=entries.next();
			/*lenCamMov=camionesEnMovimiento.size();
			if(lenCamMov>0) {
				heuristicaDesdeIni=calcularHeuristica(0,0,entry.getValue().getUbicacionX(),entry.getValue().getUbicacionY());
				for(int i=0;i<lenCamMov;i++) {//camion m�s cercano
					heuristica2=calcularHeuristica(camionesEnMovimiento.get(i).getX(), camionesEnMovimiento.get(i).getY(), entry.getValue().getUbicacionX(),entry.getValue().getUbicacionY());
					if(heuristica2<heuristica) {
						heuristica=heuristica2;
						camionMasCerca=camionesEnMovimiento.get(i);
						j=i;
					}
				}
				if(heuristica<heuristicaDesdeIni) {//si el cami�n en mov est� m�s cerca que uno en la planta principal					
					Camino cam=calcularCaminoMasCorto(camionMasCerca.getX(),camionMasCerca.getY(),
							entry.getValue().getUbicacionX(),entry.getValue().getUbicacionY());
					camionesEnMovimiento.get(j).setX(entry.getValue().getUbicacionX());
					camionesEnMovimiento.get(j).setY(entry.getValue().getUbicacionY());
					usoCamionMov=true;
					tamCam=cam.getTamano();
					System.out.println();
					System.out.print(tamCam);
					System.out.println();
					pintarCamino();
				}
			}
			if(!usoCamionMov) {
				Camino cam=calcularCaminoMasCorto(0,0,entry.getValue().getUbicacionX(),entry.getValue().getUbicacionY());
				Nodo camionSalio=new Nodo(entry.getValue().getUbicacionX(),entry.getValue().getUbicacionY());
				camionesEnMovimiento.add(camionSalio);
				tamCam=cam.getTamano();
				System.out.println();
				System.out.print(tamCam);
				System.out.println();
				pintarCamino();
			}*/
			Camino cam=calcularCaminoMasCorto(0,0,entry.getValue().getUbicacionX(),entry.getValue().getUbicacionY());
			Nodo camionSalio=new Nodo(entry.getValue().getUbicacionX(),entry.getValue().getUbicacionY());
			camionesEnMovimiento.add(camionSalio);
			tamCam=cam.getTamano();
			System.out.println();
			System.out.print(tamCam);
			System.out.println();
			pintarCamino();
			usoCamionMov=false;
			
		}
	}
	public Camino calcularCaminoMasCorto(int posIniX, int posIniY, int posFinX, int posFinY/*, int[][]mapaObstaculo*/) {
		mapa.setPosicionInicial(posIniX, posIniY);
		mapa.setPosicionMeta(posFinX, posFinY);
		
		listaCerrada=new ArrayList<Nodo>();
		listaAbierta=new ListaNodosOrdenadas();
		if(mapa.getNodo(posFinX,posFinY).getEsObstaculo()) {
			return null;
		}
		mapa.getNodoInicial().setDistanciaDesdePrincipio(0);
		listaCerrada.clear();
		listaAbierta.clear();
		listaAbierta.anadir(mapa.getNodoInicial());
		caminoMasCorto=new Camino();
		while(listaAbierta.tamano()!=0) {
			Nodo actual=listaAbierta.getPrimero();
			if(actual.getX()==mapa.getPosFinX() && actual.getY()==mapa.getPosFinY()) {
				return reconstruirCamino(actual);
			}
			listaAbierta.remover(actual);
			listaCerrada.add(actual);
			for(Nodo vecino : actual.getNeighborList()) {
				boolean esMejorVecino;
				if(listaCerrada.contains(vecino)) {
					continue;
				}
				if(!vecino.getEsObstaculo()) {
					float distanciaDesdePrincipioAlVecino=(actual.getDistanciaDesdePrincipio()+mapa.distanciaEntreNodos(actual, vecino));
					if(!listaAbierta.contiene(vecino)) {
						listaAbierta.anadir(vecino);
						esMejorVecino=true;
					}else if(distanciaDesdePrincipioAlVecino < actual.getDistanciaDesdePrincipio()) {
						esMejorVecino=true;
					}else{
						esMejorVecino=false;
					}
					if(esMejorVecino) {
						vecino.setPrevioNodo(actual);
						vecino.setDistanciaDesdePrincipio(distanciaDesdePrincipioAlVecino);
						vecino.setDistanciaHeuristicaALaMeta(calcularHeuristica(vecino.getX(), vecino.getY(), mapa.getPosFinX(), mapa.getPosFinY()));
					}
				}
			}
		}
		return caminoMasCorto;
	}
	public void pintarCamino() {
		Nodo nodo;
		for(int x=0;x<mapa.getAnchoMapa();x++) {
			if(x==0) {
				for(int i=0;i<=mapa.getAnchoMapa();i++) {
					System.out.print("-");
				}
				System.out.println();
			}
			System.out.print("|");
			for(int y=0; y<mapa.getAlturaMapa();y++) {
				nodo=mapa.getNodo(x, y);
				if(nodo.getEsObstaculo()) {
					System.out.print("-");
				}else if(nodo.getEsInicio()) {
					System.out.print("I");
				}else if(nodo.getEsMeta()) {
					System.out.print("M");
				}else if(caminoMasCorto.contiene(nodo.getX(),nodo.getY())) {
					System.out.print("*");
				}else {
					System.out.print(" ");
				}
				if(y==mapa.getAlturaMapa()) {
					System.out.print("_");
				}
			}
			System.out.print("|");
			System.out.println();
		}
		for(int i=0;i<=mapa.getAnchoMapa();i++) {
			System.out.print("-");
		}
	}
	private Camino reconstruirCamino(Nodo nodo) {
		Camino camino=new Camino();
		while(!(nodo.getPrevioNodo()==null)) {
			camino.anadirCoordenadasInicio(nodo);
			nodo=nodo.getPrevioNodo();
		}
		caminoMasCorto=camino;
		return camino;
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
			Collections.sort(lista);
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
