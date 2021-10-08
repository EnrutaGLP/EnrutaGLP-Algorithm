package com.enrutaglp.algorithm.grasp;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.enrutaglp.model.Camion;
import com.enrutaglp.model.Pedido;
import com.enrutaglp.model.Punto;

public class Ruta implements Comparable<Ruta> {
	private Map<String, Pedido> pedidos;
	private List<Punto> nodos;
	private Camion camion;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	private LocalDateTime fechaHoraTranscurrida; //inicializar con la fechahoraactual
	private int cantPedidosNoEntregados;
	private double glpNoEntregado;
	private double petroleoConsumido;
	private double costoRuta;
	private double distanciaRecorrida;
	
	public Ruta(Camion camion, String fechaActual, String horaActual) {
		this.pedidos = new HashMap<String, Pedido>();
		this.nodos = new ArrayList<Punto>();
		this.costoRuta = 0;
		Punto planta = new Punto(12, 8, 0);
		this.nodos.add(planta);
		
		this.camion = new Camion(camion);
		this.fechaHoraTranscurrida = LocalDateTime.parse(fechaActual + " " + horaActual,formatter);
		this.cantPedidosNoEntregados = 0;
		this.glpNoEntregado = 0;
		this.petroleoConsumido = 0;
		this.costoRuta = 0;
		this.distanciaRecorrida = 0;
	}
	
	
	public double getCostoRuta() {
		return costoRuta;
	}

	public void setCostoRuta(double costoRuta) {
		this.costoRuta = costoRuta;
	}

	public double calcularCostoRuta(Map<String, Pedido> pedidosOriginales,double wa, double wb, double wc) {
		this.cantPedidosNoEntregados = 0;
		this.glpNoEntregado = 0;
		this.costoRuta = wa*this.getPetroleoConsumido(); 
		try {
			for(String key: pedidosOriginales.keySet()) {
				
				if(!this.pedidos.containsKey(key)) {
					this.glpNoEntregado += pedidosOriginales.get(key).getCantidadGLP(); 
					this.cantPedidosNoEntregados += 1;
					//solo en caso el tiempo limite sea menor al tiempo actual
				}
			}
			
			this.costoRuta += wb*this.glpNoEntregado;
			return this.costoRuta;
		}catch(Exception e) {
			return this.costoRuta;
		}
		
	}
	
	public Camion getCamion() {
		return camion;
	}

	public void setCamion(Camion camion) {
		this.camion = camion;
	}

	public void copiarRuta(Ruta ruta) {
		this.camion = new Camion(ruta.getCamion());
		this.fechaHoraTranscurrida = ruta.getFechaHoraTranscurrida();
		this.setPedidos(ruta.getPedidos());
		this.setNodos(ruta.getNodos());
		
		this.costoRuta = ruta.getCostoRuta();		
		this.cantPedidosNoEntregados = ruta.getCantPedidosNoEntregados();
		this.glpNoEntregado = ruta.getGlpNoEntregado();
		this.petroleoConsumido = ruta.getPetroleoConsumido();
		this.distanciaRecorrida = ruta.getDistanciaRecorrida();
	}
	
	public int getCantPedidosNoEntregados() {
		return cantPedidosNoEntregados;
	}


	public void setCantPedidosNoEntregados(int cantPedidosNoEntregados) {
		this.cantPedidosNoEntregados = cantPedidosNoEntregados;
	}


	public double getGlpNoEntregado() {
		return glpNoEntregado;
	}


	public void setGlpNoEntregado(double glpNoEntregado) {
		this.glpNoEntregado = glpNoEntregado;
	}


	public double getPetroleoConsumido() {
		return petroleoConsumido;
	}


	public void setPetroleoConsumido(double petroleoConsumido) {
		this.petroleoConsumido = petroleoConsumido;
	}


	public double getDistanciaRecorrida() {
		return distanciaRecorrida;
	}


	public void setDistanciaRecorrida(double distanciaRecorrida) {
		this.distanciaRecorrida = distanciaRecorrida;
	}


	public LocalDateTime getFechaHoraTranscurrida() {
		return fechaHoraTranscurrida;
	}

	public void setFechaHoraTranscurrida(LocalDateTime fechaHoraTranscurrida) {
		this.fechaHoraTranscurrida = fechaHoraTranscurrida;
	}

	public List<Punto> getNodos() {
		return nodos;
	}

	public void setNodos(List<Punto> nodos) {
		this.nodos = new ArrayList<Punto>();
		Iterator<Punto> iterator = nodos.iterator();
		 
		while(iterator.hasNext())
		{
			this.nodos.add(((Punto)iterator.next()).clone());  
		}
	}


	
	public Map<String, Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Map<String, Pedido> pedidos) {
		Map<String,Pedido> copia = new HashMap<String, Pedido>(); 
		for(String key: pedidos.keySet()) {
			copia.put(key,new Pedido(pedidos.get(key)));
		}
		this.pedidos = copia;
	}


	
	public void insertarPedido(Pedido pedido) {
		pedidos.put(pedido.getCodigo(), new Pedido(pedido));
		Punto punto = new Punto(pedido.getUbicacionX(),
							pedido.getUbicacionY(), this.nodos.size(),pedido.getCodigo());
		this.nodos.add(punto);
		
		this.camion.setCargaActualGLP(this.camion.getCargaActualGLP() - pedido.getCantidadGLP());	
		double distanciaPuntos = this.calcularDistanciaPuntos(this.nodos.get(this.nodos.size()-2),punto);	
		
		double consumoPetroleo = this.camion.calcularConsumoPetroleo(distanciaPuntos);
		this.petroleoConsumido += consumoPetroleo;
		this.distanciaRecorrida += distanciaPuntos;
		
		this.camion.setCargaActualPetroleo(this.camion.getCargaActualPetroleo()-consumoPetroleo);
		
		int tiempo = (int) (distanciaPuntos/this.camion.getTipo().getVelocidadPromedio());
		
		this.fechaHoraTranscurrida = this.fechaHoraTranscurrida.plusHours(tiempo);
		
	}
	
	public void insertarPuntoPlanta() {
		
		if(!this.nodos.get(this.nodos.size()-1).isPlanta()) {
			Punto punto = new Punto(12,8, this.nodos.size());
			this.nodos.add(punto);
			this.camion.setCargaActualGLP(this.camion.getTipo().getCapacidadGLP());
			this.camion.setCargaActualPetroleo(this.camion.getTipo().getCapacidadTanque());
			
			double distanciaPuntos = this.calcularDistanciaPuntos(this.nodos.get(this.nodos.size()-2),punto);
			double consumoPetroleo = this.camion.calcularConsumoPetroleo(distanciaPuntos);
			this.petroleoConsumido += consumoPetroleo;
			this.distanciaRecorrida += distanciaPuntos;
			
			int tiempo = (int) (distanciaPuntos/this.camion.getTipo().getVelocidadPromedio());
			this.fechaHoraTranscurrida = this.fechaHoraTranscurrida.plusHours(tiempo);
		}
		
	}
	
	public boolean esFactible(Pedido pedido) {
		//boolean factible;
		
		//el camion debe tener combustible para ir al pedido y regresar a la planta
		//el camion debe tener GLP para el pedido
		//el camion debe entregar antes de la hora maxima
		try {
			Punto punto = new Punto(pedido.getUbicacionX(),
					pedido.getUbicacionY(), this.nodos.size(),pedido.getCodigo());
			
			Punto planta = new Punto(12, 8, 5000);
			
			double distanciaPuntosActualPedido = this.calcularDistanciaPuntos(this.nodos.get(this.nodos.size()-1),
										punto);
			
			double distanciaPuntosPedidoPlanta = this.calcularDistanciaPuntos(planta,
										punto);
			
			double consumoPetroleo = this.camion.calcularConsumoPetroleo(distanciaPuntosActualPedido+distanciaPuntosPedidoPlanta);
			
			//vdt = 
			int tiempo = (int) (distanciaPuntosActualPedido/this.camion.getTipo().getVelocidadPromedio());
			
			LocalDateTime fechaHoraEntrega = this.fechaHoraTranscurrida.plusHours(tiempo);
			
			if((this.camion.getCargaActualGLP()>=pedido.getCantidadGLP()) &&
					(this.camion.getCargaActualPetroleo()>=consumoPetroleo) &&
					(fechaHoraEntrega.isBefore(pedido.getFechaHoraLimite()))) {
				return true;
			}
			
			
			return false;
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public double calcularDistanciaPuntos(Punto i, Punto j) {
	  double x1 = i.getUbicacionX(); 
	  double y1 = i.getUbicacionY(); 
	  double x2 = j.getUbicacionX(); 
	  double y2 = j.getUbicacionY();
	  
	  return Math.abs(y2 - y1)+ Math.abs(x2 - x1);
	}

	@Override
	public int compareTo(Ruta o) {
		if (this.getCostoRuta()>o.getCostoRuta()){
			return 1;
		}
		else {		
			return -1;
		}

	}
		
}
