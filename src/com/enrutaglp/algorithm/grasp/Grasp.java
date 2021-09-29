package com.enrutaglp.algorithm.grasp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.enrutaglp.model.Camion;
import com.enrutaglp.model.Pedido;
import com.enrutaglp.model.Punto;

public class Grasp {
	
	List<Pedido> pedidos;
	List<Punto> nodosRecorridos;
	int numCandidatos;
	Camion camion;
	String fechaActual; 
	String horaActual;

	public Ruta construirSolucion(int k) {	
		
		Ruta ruta = new Ruta(this.camion, fechaActual, horaActual);
		
		List<Pedido> pedidosSolucion = pedidos;
		boolean termino = true;
		
		while(true) {
			
			//generas N max candidatos aleatoriamente (nodosRecorridos)
			List<Ruta> rutasCandidatos = this.generarCandidatos(ruta, pedidosSolucion);
			//seleccionas 1 de los X mejores candidatos de los N generados
			
			Ruta rutaSeleccionada = this.seleccionarCandidato(k, rutasCandidatos);
			
			//si el ultimo punto era planta y el sigueinte tambien es planta entonces no hay mas solucionesfactibles
			if((rutaSeleccionada.getNodos().get(rutaSeleccionada.getNodos().size()-1).isPlanta()) &&
                    (ruta.getNodos().get(ruta.getNodos().size()-1).isPlanta())){
                        return ruta;
                    }
			//para este punto hay 3 opciones P-NP, NP-P o NP-NP
			
			//si la sigueinte ruta no es planta, entonces se remueve el pedido
			if(!(rutaSeleccionada.getNodos().get(rutaSeleccionada.getNodos().size()-1).isPlanta())) {
				pedidosSolucion.remove(rutaSeleccionada.getPedidos().get(rutaSeleccionada.getPedidos().size()-1));	
			}
			
			//se copia la ruta ya sea de P-NP, NP-P o NP-NP
			//entonces la ultima ruta es NP o P
			ruta.copiarRuta(rutaSeleccionada);
			
			//si ya no hay mas pedidos entonces deberia terminar el algoritmo
			if(pedidosSolucion.size()==0) {
				//ssi no es planta entonces se inserta un punto planta al final
				if(!(ruta.getNodos().get(ruta.getNodos().size()-1).isPlanta())) {
					ruta.insertarPuntoPlanta();
				}
				return ruta;
			}
			//si pedidos y P y NP
			else {
				//si es planta entonces se verifica que haya al menos un pedido factible
				if(ruta.getNodos().get(ruta.getNodos().size()-1).isPlanta()) {
					for (int i = 0; i < pedidosSolucion.size(); i++) {
						//si es planta y hay al menos uno factible, continua
						if(ruta.esFactible(pedidosSolucion.get(i))) {
							break;
						}
						//si es planta y no hay factibles, entonces termina
						else {
							if(i==pedidosSolucion.size()-1) {
								return ruta;
							}
							continue;
						}
					}
				}
				
			}
			
			
		}
		
	}
	
	
	public List<Ruta> generarCandidatos(Ruta ruta, List<Pedido> pedidos){
		
		List<Ruta> rutasCandidatos = new ArrayList<Ruta>();
		List<Pedido> pedidosSolucion = pedidos;
		
		for(int i=0; i<this.numCandidatos; i++) {
			Ruta rutaGenerada = new Ruta(ruta.getCamion(), this.fechaActual, this.horaActual);
			
			rutaGenerada.copiarRuta(ruta);
			
			for (int j = 0; j < pedidosSolucion.size(); j++) {
				boolean esFactible = rutaGenerada.esFactible(pedidosSolucion.get(j));
				
				if(esFactible) {
					rutaGenerada.insertarPedido(pedidosSolucion.get(j));
					pedidosSolucion.remove(j);
					rutasCandidatos.add(rutaGenerada);
					break;
				}
				else {
					pedidosSolucion.remove(j);
				}
				if(j==pedidosSolucion.size()-1) {
					//regresar a la planta
					rutaGenerada.insertarPuntoPlanta();
				}
			}
			
		}
		
		return rutasCandidatos;
	}

	public Ruta seleccionarCandidato(int k, List<Ruta> rutasCandidatos) {		
		Collections.sort(rutasCandidatos); 
		
		int longitud = rutasCandidatos.size()>k? k : rutasCandidatos.size(); 
		
		
		int random = ThreadLocalRandom.current().nextInt(0, longitud);
		
		Ruta rutaElegida = rutasCandidatos.get(random);
		
		return rutaElegida;
	}
	

	public void run() {
		
		double mejorCosto = 0;
		
		while(true) {
			Ruta rutaSolucion = this.construirSolucion();
			rutaSolucion = this.busquedaLocal(rutaSolucion);
			double costoSolucion = rutaSolucion.calcularCosto();
			
			if(costoSolucion>mejorCosto) {
				mejorCosto = costoSolucion;
			}
			else {
				break;
			}
		}
		
	}
	

	
	public Ruta busquedaLocal() {
		float ruta;
		
		return ruta;	
	}
	
	
	
}
