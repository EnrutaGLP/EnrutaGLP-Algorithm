package com.enrutaglp.astarAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Collections;

import com.enrutaglp.model.Camion;
import com.enrutaglp.model.Pedido;
import com.enrutaglp.model.Planta;
import com.enrutaglp.model.EntregaPedido;

import java.time.Duration;
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
	public void setPedidos(Map<String,Pedido> ped) {
		this.pedidos=ped;
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
		double MaxGLPofCamiones = Astar.this.camionesImportantes.camionConMasCapacidad.getCargaActualGLP();
		
		int cod = pedidos.size() + 1;//Codigo que recibiran los pedidos divididos: Ultimo codigo más uno 
		for (Map.Entry<String, Pedido> pedido: pedidos.entrySet()) {
			double cantidadGLPPedido = pedido.getValue().getCantidadGLP();
			if (cantidadGLPPedido > MaxGLPofCamiones) {
				
				while (cantidadGLPPedido > 0) {
					// CREO PEDIDO DIVIDIDO 
					String[]fechaHoraLimiteStr = pedido.getValue().getFechaHoraLimite().toString().split(" ");
					String fechaLimite = fechaHoraLimiteStr[0];
					String horaLimite = fechaHoraLimiteStr[1];
					Pedido pedidoDividido = new Pedido(String.valueOf(cod), pedido.getValue().getCliente(),
							0, pedido.getValue().getUbicacionX(), pedido.getValue().getUbicacionY(),
							fechaLimite, horaLimite);
						//El pedido tiene una cantidad de GLP de 0 inicialmente, se cambia a continuación
					pedidoDividido.setFechaHoraPedido(pedido.getValue().getFechaHoraPedido());
						//Por analizar si es correcto la asignación de este valor en cada pedido dividido
					pedidoDividido.setFechaHoraCompletado(pedido.getValue().getFechaHoraCompletado());
						//Por analizar si es correcto la asignación de este valor en cada pedido dividido
					pedidoDividido.setEstado(pedido.getValue().getEstado());
						//Por analizar si es correcto la asignación de este valor en cada pedido dividido
					
					// 
					if (cantidadGLPPedido > MaxGLPofCamiones) {
						pedidoDividido.setCantidadGLP(MaxGLPofCamiones);
						cantidadGLPPedido -= MaxGLPofCamiones;
					}
					else {
						pedidoDividido.setCantidadGLP(cantidadGLPPedido);
						cantidadGLPPedido = 0;
					}
					cod ++;
					pedidos.put(pedidoDividido.getCodigo(), pedidoDividido);
	
				}
				
				//Eliminar pedido que fue dividido
				pedidos.remove(pedido.getKey());
			}
		}
		
	}
	public void ordenarCamionesPorCapacidadGLP() {
		
	}
	public String resolverPedidos() {
		camionesImportantes=hallarCamionesImportantes();
		float velocidadMasBaja=(float)camionesImportantes.getCamionMasLento().getTipo().getVelocidadPromedio();
		if(camionesImportantes.getCamionMasLento()==null) {
			return " ";
		}
		validarPedidos();
		
		float petroleoTotal = 0;
		double glpNoEntregado = 0;
		double glpTotalPedidos = 0;
		int pedidosNoEntregados = 0;
		int pedidosTotales = 0;
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
			
			glpTotalPedidos+=entry.getValue().getCantidadGLP();
			pedidosTotales++;
			
			horasParaLlegar=tamCamino/velocidadMasBaja;
			horasInt=Math.round(horasParaLlegar);
			horasDecimal=horasParaLlegar-horasInt;
			minInt=(int)Math.ceil(horasDecimal*60);
			
			horaMaximaEntrega=entry.getValue().getFechaHoraLimite().minusHours(horasInt);
			horaMaximaEntrega=horaMaximaEntrega.minusMinutes(minInt);
			pedidoPreEnruta.setFechaMaximaEntrega(horaMaximaEntrega);
			
			pedidosPreEnrutados.add(pedidoPreEnruta);
			
			//System.out.println();
			//System.out.print(tamCamino);
			//System.out.println();
			//pintarCamino();
			resetearMapa();
		}
		Collections.sort(pedidosPreEnrutados);
		
		//ArrayList<Nodo> camionesEnMovimiento;
		//camionesEnMovimiento=new ArrayList<Nodo>();
		Map<String,EntregaPedido> entregaPedidos=new HashMap<String, EntregaPedido>();
		ArrayList<String> hashCamionesMovimiento=new ArrayList<String>();
		ArrayList<Integer> indiceCamionesADejarMover=new ArrayList<Integer>();
		ArrayList<EntregaPedido> entregaPedidosImprimir=new ArrayList<EntregaPedido>();
		String hashCamion;
		float velocidadCamion;
		double consumoPetroleo=0;
		LocalDateTime horaEntrega;
		boolean esColapso=false;
		int numPedAtendidos=0;

		
		//Se supone que ningún camión se va a desviar de su ruta original hasta entregar su pedido
		for(PedidoPreEnrutado pedidoEnrutado:pedidosPreEnrutados) {			
			if(hashCamionesMovimiento.size()>0) {
				for(int i=0;i<hashCamionesMovimiento.size();i++) {
					if(entregaPedidos.get(hashCamionesMovimiento.get(i)) == null) {
						continue;
					}else {
						int estaEnMovimiento=posicionCamion(pedidoEnrutado.getFechaMaximaEntrega(),
								entregaPedidos.get(hashCamionesMovimiento.get(i)));
						if(estaEnMovimiento==0) {//redirigir un camion que está regresando
							//indiceCamionesADejarMover.add(i);
						}else if(estaEnMovimiento==1) {
							indiceCamionesADejarMover.add(i);
						}
					}
				}
				for(int j=indiceCamionesADejarMover.size()-1;j>=0;j--) {//se quita de la lista los camiones que ya llegaron a la planta principal
					int indexABorrar=indiceCamionesADejarMover.get(j);
					flota.get(hashCamionesMovimiento.get(indexABorrar)).setCargaActualGLP(flota.get(hashCamionesMovimiento.get(indexABorrar)).getTipo().getCapacidadGLP());
					flota.get(hashCamionesMovimiento.get(indexABorrar)).setCargaActualPetroleo(flota.get(hashCamionesMovimiento.get(indexABorrar)).getTipo().getCapacidadTanque());
					hashCamionesMovimiento.remove(indexABorrar);
				}
				indiceCamionesADejarMover.clear();
			}
			hashCamion=obtenerCamionBestFit2(pedidoEnrutado.getPedido().getCantidadGLP(),hashCamionesMovimiento);
			if(hashCamion==" ") {//colapso logistico
				esColapso=true;
				glpNoEntregado += pedidoEnrutado.getPedido().getCantidadGLP();
				pedidosNoEntregados++;
				//break;
			}else {
				hashCamionesMovimiento.add(hashCamion);
				
				consumoPetroleo=flota.get(hashCamion).calcularConsumoPetroleo(tamCamino-1);
				petroleoTotal += consumoPetroleo*2;
				if((consumoPetroleo+consumoPetroleo)>flota.get(hashCamion).getCargaActualPetroleo()) {
					continue;//se debe añadir como pedido imposible
					//para luego validad si los camiones con más capacidad pueden manejar el pedido
				}
				
				velocidadCamion=(float)flota.get(hashCamion).getTipo().getVelocidadPromedio();
				tamCamino=pedidoEnrutado.getCamino().getTamano();
				horasParaLlegar=tamCamino/velocidadCamion;
				horasInt=Math.round(horasParaLlegar);
				horasDecimal=horasParaLlegar-horasInt;
				minInt=(int)Math.ceil(horasDecimal*60);
				
				horaEntrega=pedidoEnrutado.getFechaMaximaEntrega().plusHours(horasInt);
				horaEntrega=horaEntrega.plusMinutes(minInt);
				
				flota.get(hashCamion).setCargaActualPetroleo(flota.get(hashCamion).getCargaActualPetroleo()-consumoPetroleo);
				flota.get(hashCamion).setCargaActualGLP(flota.get(hashCamion).getCargaActualGLP()-pedidoEnrutado.getPedido().getCantidadGLP());
				
				
				EntregaPedido entregaPed=new EntregaPedido(pedidoEnrutado.getPedido().getCantidadGLP(),
						horaEntrega,pedidoEnrutado.getFechaMaximaEntrega(),consumoPetroleo,flota.get(hashCamion),
						pedidoEnrutado.getPedido());
				//hashCamion=hashCamion+"numPedAtendidos";
				entregaPedidos.put(hashCamion, entregaPed);
				entregaPedidosImprimir.add(entregaPed);
				numPedAtendidos++;
			}
		}
		
		String datosAEvaluar=String.valueOf(petroleoTotal);
		datosAEvaluar.concat(",").
		concat(String.valueOf(glpNoEntregado)).concat(",").concat(String.valueOf(glpTotalPedidos)).
		concat(",").concat(String.valueOf(pedidosNoEntregados)).concat(",").
		concat(String.valueOf(pedidosTotales)).concat("\n");
		return datosAEvaluar;
	}
	public String obtenerCamionBestFit(double cargaGLP) {//suponiendo que ya está ordenado ascendentemente
		Iterator<Map.Entry<String,Camion>> entries = flota.entrySet().iterator();
		while(entries.hasNext()) {
			Map.Entry<String,Camion> entry=entries.next();
			
		}
		return "a";
	}
	public int posicionCamion(LocalDateTime horaEvaluacion, EntregaPedido entregaPedido) {
		//-1 si aún no llega, 0 si ya lo entrego y/o anda de vuelta, 1 si ya volvió a la planta principal
		if(entregaPedido.getHoraEntregada().isBefore(horaEvaluacion) || entregaPedido.getHoraEntregada().isEqual(horaEvaluacion)) {//ya se entrego
			//saber si ya volvio a la planta principal
			
			//falta actualizar las cargas de petroleo y glp
			//se actualiza en la funcion de arriba si aún no vuelve a la planta principal
			//falta actualizar las cargas si es que ya llego a la planta principal
			
			LocalDateTime aux=entregaPedido.getHoraSalida();
			
			Duration duration=Duration.between(entregaPedido.getHoraEntregada(), aux);
			long seconds=Math.abs(duration.getSeconds());		
			aux=aux.plusSeconds(seconds);
			if(horaEvaluacion.isAfter(aux)||horaEvaluacion.isEqual(aux)) {
				return 1;
			}else {
				return 0;
			}
		}else {//aun no se entrega
			return -1;
		}
	}
	public String obtenerCamionBestFit2(double cargaGLP, ArrayList<String> camionesEnMovimiento) {
        //Retorna codigo de camion que mejor satisface la cargaGLP necesaria
        double bestCargaGLP = 9999; //Variable auxiliar para guardar en cada iteración la mejor cargaGLP encontrada
        Map.Entry<String, Camion> bestBook = null; //VAriable auxiliar para guardar mejor entrada(String, Camion)
        for (Map.Entry<String, Camion> book: flota.entrySet()) {
            double cargaGLPofBook = book.getValue().getTipo().getPesoGLP();
            if (cargaGLPofBook >= cargaGLP && bestCargaGLP > cargaGLPofBook
                    && !camionesEnMovimiento.contains(book.getKey())) {
                bestBook = book;
            }
        }
        if (bestBook == null)
            return " ";
        return bestBook.getKey();
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
