package com.enrutaglp.astarAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Iterator;
import java.util.Collections;

import com.enrutaglp.model.Camion;
import com.enrutaglp.model.Pedido;
import com.enrutaglp.model.Planta;
import java.time.LocalDateTime;

public class Astar { 
	Map<String,Pedido>pedidos; 
	Map<String,Camion>flota;
	Mapa mapa;
	Camino caminoMasCorto;
	ArrayList<Nodo> listaCerrada;
	ListaNodosOrdenadas listaAbierta;
	LocalDateTime fechaIniSimulacion;
	CamionesImportantes camionesImportantes;
	Planta plantaPrincipal;
	
	public Astar(int mapX, int mapY, Map<String,Pedido>pedidos, Map<String,Camion>flota) {
		this.mapa= new Mapa(mapX,mapY);
		this.pedidos = pedidos; 
		this.flota = flota;
		listaCerrada=new ArrayList<Nodo>();
		listaAbierta=new ListaNodosOrdenadas();
		caminoMasCorto= new Camino();
		camionesImportantes=new CamionesImportantes();
	}
	public Astar(int mapX, int mapY) {
		this.mapa= new Mapa(mapX,mapY);
		listaCerrada=new ArrayList<Nodo>();
		listaAbierta=new ListaNodosOrdenadas();
		caminoMasCorto= new Camino();
		camionesImportantes=new CamionesImportantes();
	}
	public Astar(int mapX, int mapY, Map<String,Pedido>pedidos, Map<String,Camion>flota, LocalDateTime fechaYHoraIni, Planta planta) {
		this.mapa= new Mapa(mapX,mapY);
		this.pedidos = pedidos; 
		this.flota = flota;
		listaCerrada=new ArrayList<Nodo>();
		listaAbierta=new ListaNodosOrdenadas();
		caminoMasCorto= new Camino();
		camionesImportantes=new CamionesImportantes();
		fechaIniSimulacion=fechaYHoraIni;
		plantaPrincipal=planta;
	}
	public CamionesImportantes hallarCamionesImportantes() {
		double velocidadMinima=9999999;
		double capacidadMaxima=-1;
		Iterator<Map.Entry<String,Camion>> entries = flota.entrySet().iterator();
		Camion camionLento=null;
		Camion camionCapacidad=null;
		while(entries.hasNext()) {
			Map.Entry<String,Camion> entry=entries.next();
			if(entry.getValue().getTipo().getVelocidadPromedio()<velocidadMinima) {
				velocidadMinima=entry.getValue().getTipo().getVelocidadPromedio();
				camionLento=entry.getValue();
			}
			if(entry.getValue().getTipo().getCapacidadGLP()>capacidadMaxima) {
				capacidadMaxima=entry.getValue().getTipo().getCapacidadGLP();
				camionCapacidad=entry.getValue();
			}
		}
		CamionesImportantes camImp=new CamionesImportantes();
		camImp.setCamionConMasCapacidad(camionCapacidad);
		camImp.setCamionMasLento(camionLento);
		return camImp;
	}
	public void validarPedidos() {//si un pedido supera la capacidad del camion máximo se debe separar en 2
		
	}
	public void ordenarCamionesPorCapacidadGLP() {
		
	}
	public void resolverPedidos() {
		camionesImportantes=hallarCamionesImportantes();
		float velocidadMasBaja=(float)camionesImportantes.getCamionMasLento().getTipo().getVelocidadPromedio();
		if(camionesImportantes.getCamionMasLento()==null) {
			return;
		}
		validarPedidos();
		
		int tamCamino=0;
		float horasParaLlegar=0;
		int horasInt=0;
		float horasDecimal=0;
		int minInt=0;
		LocalDateTime horaMaximaEntrega;
		
		Iterator<Map.Entry<String,Pedido>> entries = pedidos.entrySet().iterator();
		ArrayList<PedidoPreEnrutado> pedidosPreEnrutados=new ArrayList<PedidoPreEnrutado>();
		while(entries.hasNext()) {
			Map.Entry<String,Pedido> entry=entries.next();
			Camino camino=calcularCaminoMasCorto(0,0,entry.getValue().getUbicacionX(),entry.getValue().getUbicacionY());
			//Nodo camionSalio=new Nodo(entry.getValue().getUbicacionX(),entry.getValue().getUbicacionY());
			//camionesEnMovimiento.add(camionSalio);
			
			PedidoPreEnrutado pedidoPreEnruta=new PedidoPreEnrutado(entry.getValue(), camino);
			tamCamino=camino.getTamano();
			horasParaLlegar=tamCamino/velocidadMasBaja;
			horasInt=Math.round(horasParaLlegar);
			horasDecimal=horasParaLlegar-horasInt;
			minInt=(int)Math.ceil(horasDecimal*60);
			
			horaMaximaEntrega=entry.getValue().getFechaHoraLimite().minusHours(horasInt);
			horaMaximaEntrega=horaMaximaEntrega.minusMinutes(minInt);
			pedidoPreEnruta.setFechaMaximaEntrega(horaMaximaEntrega);
			
			pedidosPreEnrutados.add(pedidoPreEnruta);
			
			System.out.println();
			System.out.print(tamCamino);
			System.out.println();
			pintarCamino();
			resetearMapa();
		}
		Collections.sort(pedidosPreEnrutados);
		
		//ArrayList<Nodo> camionesEnMovimiento;
		//camionesEnMovimiento=new ArrayList<Nodo>();
		ArrayList<String> hashCamionesMovimiento=new ArrayList<String>();
		double heuristica=Float.MAX_VALUE;
		double heuristica2=0;
		double heuristicaDesdeIni=0;
		Nodo camionMasCerca=new Nodo(0,0);
		boolean usoCamionMov=false;
		String hashCamion;
		for(PedidoPreEnrutado pedidoEnrutado:pedidosPreEnrutados) {			
			if(hashCamionesMovimiento.size()>0) {
				
			}else {
				
			}
		}
	}
	public String obtenerCamionBestFit(double cargaGLP) {//suponiendo que ya está ordenado ascendentemente
		Iterator<Map.Entry<String,Camion>> entries = flota.entrySet().iterator();
		while(entries.hasNext()) {
			Map.Entry<String,Camion> entry=entries.next();
			
		}
		return "a";
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
	public void resetearMapa() {
		this.mapa.clear(null);
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
	static class CamionesImportantes{
		Camion camionMasLento;
		Camion camionConMasCapacidad;
		
		public CamionesImportantes() {
			camionMasLento=null;
			camionConMasCapacidad=null;
		}
		public void setCamionMasLento(Camion camion) {
			camionMasLento=camion;
		}
		public Camion getCamionMasLento() {
			return camionMasLento;
		}
		public void setCamionConMasCapacidad(Camion camion) {
			camionConMasCapacidad=camion;
		}
		public Camion getCamionConMasCapacidad() {
			return camionConMasCapacidad;
		}
	}
	static class PedidoPreEnrutado implements Comparable<PedidoPreEnrutado>{
		private Pedido pedido;
		LocalDateTime fechaMaximaEntrega;
		Camino camino;
		
		public PedidoPreEnrutado(Pedido ped, Camino cam){
			pedido=ped;
			camino=cam;
		}
		
		public void setCamino(Camino cam) {
			camino=cam;
		}
		public Camino getCamino() {
			return camino;
		}
		public void setPedido(Pedido ped) {
			pedido=ped;
		}
		public Pedido getPedido() {
			return pedido;
		}
		public void setFechaMaximaEntrega(LocalDateTime fecha) {
			fechaMaximaEntrega=fecha;
		}
		public LocalDateTime getFechaMaximaEntrega() {
			return fechaMaximaEntrega;
		}
		@Override
		public int compareTo(PedidoPreEnrutado ped) {
			if(fechaMaximaEntrega.isBefore(ped.getFechaMaximaEntrega())) {
				return -1;
			}
			if(fechaMaximaEntrega.isEqual(ped.getFechaMaximaEntrega())) {
				return 0;
			}
			return 1;
		}
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
