package com.enrutaglp.algorithm.grasp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.enrutaglp.model.Camion;
import com.enrutaglp.model.Pedido;
import com.enrutaglp.model.Punto;

public class Ruta implements Comparable<Ruta> {
	private List<Pedido> pedidos;
	private List<Punto> nodos;
	private Camion camion;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	private LocalDateTime fechaHoraTranscurrida; //inicializar con la fechahoraactual
	private double costoRuta;
	
	public double getCostoRuta() {
		return costoRuta;
	}

	public void setCostoRuta(double costoRuta) {
		this.costoRuta = costoRuta;
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
		this.nodos = nodos;
	}

	public Ruta(Camion camion, String fechaActual, String horaActual) {
		this.pedidos = new ArrayList<Pedido>();
		this.nodos = new ArrayList<Punto>();
		this.costoRuta = 0;
		Punto planta = new Punto(0, 0, 0);
		this.nodos.add(planta);
		
		this.camion = camion;
		this.fechaHoraTranscurrida = LocalDateTime.parse(fechaActual + " " + horaActual,formatter);
	}
	
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
		/*
		for (int i = 0; i < this.pedidos.size(); i++) {
			Punto punto = new Punto(this.pedidos.get(i).getUbicacionX(),
									this.pedidos.get(i).getUbicacionY(), i);
			
			this.nodos.add(punto);
			
			if(punto.isPlanta()) {
				this.camion.setCargaActualGLP(this.camion.getTipo().getCapacidadGLP());
				this.camion.setCargaActualPetroleo(this.camion.getTipo().getCapacidadTanque());
			}
			else {
				this.camion.setCargaActualGLP(this.camion.getCargaActualGLP() - this.pedidos.get(i).getCantidadGLP());	
				double distanciaPuntos = this.calcularDistanciaPuntos(this.nodos.get(i-1),this.nodos.get(i));				
				this.camion.setCargaActualPetroleo(this.camion.getCargaActualPetroleo()-this.camion.calcularConsumoPetroleo(distanciaPuntos));
			}
			
		}
		*/
	}


	
	public void insertarPedido(Pedido pedido) {
		pedidos.add(pedido);
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
		
		if((this.camion.getCargaActualGLP()>=pedido.getCantidadGLP()) & (this.camion.getCargaActualPetroleo()>=consumoPetroleo) & (fechaHoraEntrega.isAfter(pedido.getFechaHoraLimite()))) {
			return true;
		}
		
		
		return false;
	}
	
	public double calcularDistanciaPuntos(Punto i, Punto j) {
	  double x1 = i.getUbicacionX(); 
	  double y1 = i.getUbicacionY(); 
	  double x2 = j.getUbicacionX(); 
	  double y2 = j.getUbicacionY();
	  
	  return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
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
