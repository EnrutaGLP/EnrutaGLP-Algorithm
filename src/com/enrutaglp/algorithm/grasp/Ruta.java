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
	private double costoRuta;
	
	
	public Ruta(Camion camion, String fechaActual, String horaActual) {
		this.pedidos = new HashMap<String, Pedido>();
		this.nodos = new ArrayList<Punto>();
		this.costoRuta = 0;
		Punto planta = new Punto(0, 0, 0);
		this.nodos.add(planta);
		
		this.camion = camion;
		this.fechaHoraTranscurrida = LocalDateTime.parse(fechaActual + " " + horaActual,formatter);
	}
	
	
	public double getCostoRuta() {
		return costoRuta;
	}

	public void setCostoRuta(double costoRuta) {
		this.costoRuta = costoRuta;
	}

	public double calcularCostoRuta(Map<String, Pedido> pedidosOriginales,double wa, double wb, double wc) {
		try {
			double consumoPetroleo = 0;
			for (int i = 1; i < this.nodos.size(); i++) {
				double distanciaPuntos = this.calcularDistanciaPuntos(this.nodos.get(i),this.nodos.get(i-1));
				consumoPetroleo += this.camion.calcularConsumoPetroleo(distanciaPuntos);
			}
			
			double noEntregaATiempo = 0;
			double tiempoAdicional = 0;
			for(String key: pedidosOriginales.keySet()) {
				if(!pedidos.containsKey(key)) {
					noEntregaATiempo += 1;
					double duracion = Duration.between(this.fechaHoraTranscurrida, pedidosOriginales.get(key).getFechaHoraLimite() ).toHours();
					tiempoAdicional += duracion;
				}
			}
			
			this.costoRuta = wa*consumoPetroleo + wb*noEntregaATiempo + wc*tiempoAdicional;
			return this.costoRuta;
		}catch(Exception e) {
			return 0;
		}
		
	}
	
	public Camion getCamion() {
		return camion;
	}

	public void setCamion(Camion camion) {
		this.camion = camion;
	}

	public void copiarRuta(Ruta ruta) {
		this.camion = ruta.getCamion();
		this.fechaHoraTranscurrida = ruta.getFechaHoraTranscurrida();
		this.setPedidos(ruta.getPedidos());
		this.setNodos(ruta.getNodos());
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
		copia.putAll(pedidos);
		this.pedidos = copia;
	}


	
	public void insertarPedido(Pedido pedido) {
		pedidos.put(pedido.getCodigo(), pedido);
		Punto punto = new Punto(pedido.getUbicacionX(),
							pedido.getUbicacionY(), this.nodos.size());
		this.nodos.add(punto);
		
		this.camion.setCargaActualGLP(this.camion.getCargaActualGLP() - pedido.getCantidadGLP());	
		double distanciaPuntos = this.calcularDistanciaPuntos(this.nodos.get(this.nodos.size()-2),punto);				
		this.camion.setCargaActualPetroleo(this.camion.getCargaActualPetroleo()-this.camion.calcularConsumoPetroleo(distanciaPuntos));
		
		int tiempo = (int) (distanciaPuntos/this.camion.getTipo().getVelocidadPromedio());
		
		this.fechaHoraTranscurrida = this.fechaHoraTranscurrida.plusHours(tiempo);
	}
	
	public void insertarPuntoPlanta() {
		Punto punto = new Punto(0,0, this.nodos.size());
		this.nodos.add(punto);
		this.camion.setCargaActualGLP(this.camion.getTipo().getCapacidadGLP());
		this.camion.setCargaActualPetroleo(this.camion.getTipo().getCapacidadTanque());
		
		double distanciaPuntos = this.calcularDistanciaPuntos(this.nodos.get(this.nodos.size()-2),punto);
		int tiempo = (int) (distanciaPuntos/this.camion.getTipo().getVelocidadPromedio());
		this.fechaHoraTranscurrida = this.fechaHoraTranscurrida.plusHours(tiempo);
	}
	
	public boolean esFactible(Pedido pedido) {
		//boolean factible;
		
		//el camion debe tener combustible para ir al pedido y regresar a la planta
		//el camion debe tener GLP para el pedido
		//el camion debe entregar antes de la hora maxima
		try {
			Punto punto = new Punto(pedido.getUbicacionX(),
					pedido.getUbicacionY(), this.nodos.size());
			
			Punto planta = new Punto(0, 0, 5000);
			
			
			
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
		// TODO Auto-generated method stub
		if (this.getCostoRuta()>o.getCostoRuta()){
			return 1;
		}
		else {		
			return -1;
		}

	}
	
	
	
}
