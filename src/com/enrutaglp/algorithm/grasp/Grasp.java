package com.enrutaglp.algorithm.grasp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import com.enrutaglp.model.Camion;
import com.enrutaglp.model.Pedido;
import com.enrutaglp.model.Punto;

public class Grasp {
	
	private Map<String, Pedido> pedidos;
	private List<Punto> nodosRecorridos;
	private int numCandidatos;
	private int k;
	private Camion camion;
	private String fechaActual; 
	private String horaActual;
	private double wa;
	private double wb;
	private double wc;
	private int cantPedidosNoEntregados;
	private double glpNoEntregado;
	private double petroleoConsumido;
	private double distanciaRecorrida;

	
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
	
	public Grasp(Map<String, Pedido> pedidos, Camion camion, String fechaActual, String horaActual, double wa, double wb, double wc) {
		this.pedidos = generarCopia(pedidos);
		this.camion = new Camion(camion);
		this.fechaActual = fechaActual;
		this.horaActual = horaActual;
		this.numCandidatos = this.pedidos.size();
		this.k = (int) Math.ceil(0.45*this.numCandidatos);
		this.wa = wa;
		this.wb = wb;
		this.wc = wc;
		this.cantPedidosNoEntregados = 0;
		this.glpNoEntregado = 0;
		this.petroleoConsumido = 0;
		this.distanciaRecorrida = 0;
	}

	public Ruta construirSolucion() {	
		
		Ruta ruta = new Ruta(this.camion, fechaActual, horaActual);
		
		Map<String, Pedido> pedidosSolucion = generarCopia(pedidos);
		
		while(true) {
			
			//generas N max candidatos aleatoriamente (nodosRecorridos)
			List<Ruta> rutasCandidatos = this.generarCandidatos(ruta, pedidosSolucion);
			//seleccionas 1 de los X mejores candidatos de los N generados
			
			Ruta rutaSeleccionada = this.seleccionarCandidato(rutasCandidatos);
			
			//si el ultimo punto era planta y el sigueinte tambien es planta entonces no hay mas solucionesfactibles
			if((rutaSeleccionada.getNodos().get(rutaSeleccionada.getNodos().size()-1).isPlanta()) &&
                    (ruta.getNodos().get(ruta.getNodos().size()-1).isPlanta())){
                        return ruta;
           }
			//para este punto hay 3 opciones P-NP, NP-P o NP-NP
			
			//si la sigueinte ruta no es planta, entonces se remueve el ultimo pedido ingresado a la lista 
			if(!(rutaSeleccionada.getNodos().get(rutaSeleccionada.getNodos().size()-1).isPlanta())) {
				String ultimoPedido = rutaSeleccionada.getNodos().get(rutaSeleccionada.getNodos().size()-1).getCodigoPedido();
				pedidosSolucion.remove(ultimoPedido);		
				
			}
			
			//se copia la ruta ya sea de P-NP, NP-P o NP-NP
			//entonces la ultima ruta es NP o P
			ruta.copiarRuta(rutaSeleccionada);
			
			//si ya no hay mas pedidos entonces deberia terminar el algoritmo
			if(pedidosSolucion.size()==0) {
				//ssi no es planta entonces se inserta un punto planta al final
				if(!(ruta.getNodos().get(ruta.getNodos().size()-1).isPlanta())) {
					ruta.insertarPuntoPlanta();
					ruta.calcularCostoRuta(this.pedidos, this.wa, this.wb, this.wc);
				}
				return ruta;
			}
			//si pedidos y P y NP
			else {
				//si es planta entonces se verifica que haya al menos un pedido factible
				if(ruta.getNodos().get(ruta.getNodos().size()-1).isPlanta()) {
					
					int it=0;
					for(String key: pedidosSolucion.keySet()) {
						//si es planta y hay al menos uno factible, continua
						if(ruta.esFactible(pedidosSolucion.get(key))) {
							break;
						}
						//si ningun pedido es factible, retorna la ruta que ya termina en la planta
						if(it==pedidosSolucion.size()-1) {
							return ruta;
						}
						it ++;
					}
				}	
			}
				
		}	
	}
		
	public Map<String,Pedido> generarCopia(Map <String,Pedido> mapa){
		Map<String,Pedido> copia = new HashMap<String, Pedido>(); 
		for(String key: mapa.keySet()) {
			copia.put(key,new Pedido(mapa.get(key)));
		}
		return copia;
	}
	
	public List<Ruta> generarCandidatos(Ruta ruta, Map<String, Pedido> pedidosCandidtos){
		List<Ruta> rutasCandidatos = new ArrayList<Ruta>();
		Map<String,Pedido> pedidosCand = generarCopia(pedidosCandidtos);
		 
		for(int i=0; i<this.numCandidatos; i++) {
			Ruta rutaGenerada = new Ruta(ruta.getCamion(), this.fechaActual, this.horaActual);
			
			rutaGenerada.copiarRuta(ruta);

			Map<String, Pedido> pedidosSolucion = generarCopia(pedidosCand);
			int j = 0;
			for(String key: pedidosSolucion.keySet()) {
				boolean esFactible = rutaGenerada.esFactible(pedidosSolucion.get(key));
				
				if(esFactible) {
					rutaGenerada.insertarPedido(new Pedido(pedidosSolucion.get(key)));
					pedidosCand.remove(key);
					j++;
					break;
				}
				else {
					pedidosCand.remove(key);
				}
				if(j==pedidosSolucion.size()-1) {
					//regresar a la planta
					if(!rutaGenerada.getNodos().get(rutaGenerada.getNodos().size()-1).isPlanta()) {
						rutaGenerada.insertarPuntoPlanta();		
					}
				}
				j++;
			}
			if(j==0 && pedidosSolucion.size()==0) {
				rutaGenerada.insertarPuntoPlanta();		
			}
			rutaGenerada.calcularCostoRuta(this.pedidos, this.wa, this.wb, this.wc);
			rutasCandidatos.add(rutaGenerada);
		}
		
		return rutasCandidatos;
	}

	public Ruta seleccionarCandidato(List<Ruta> rutasCandidatos) {		
		Collections.sort(rutasCandidatos); 
		
		int longitud = rutasCandidatos.size()>this.k? this.k : rutasCandidatos.size(); 
		int random = 0;
		try {
			random = ThreadLocalRandom.current().nextInt(0, longitud);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		Ruta rutaElegida = rutasCandidatos.get(random);
		
		return rutaElegida;
	}
	

	public Ruta run(int maxIterNoImp) {
		
		double mejorCosto = 1000000000;
		
		int nbIterNoImp = 1; 
		Ruta mejorRuta = null; 
		while(nbIterNoImp<=maxIterNoImp) {
			Ruta rutaSolucion = this.construirSolucion();
			//rutaSolucion = this.busquedaLocal(rutaSolucion);
			double costoSolucion = rutaSolucion.calcularCostoRuta(this.pedidos, this.wa, this.wb, this.wc);
			
			if(costoSolucion<mejorCosto) {
				mejorCosto = costoSolucion;
				mejorRuta = rutaSolucion; 
				nbIterNoImp = 1; 
			}
			else {
				nbIterNoImp ++;
			}
		}
		
		this.cantPedidosNoEntregados = mejorRuta.getCantPedidosNoEntregados();
		this.glpNoEntregado = mejorRuta.getGlpNoEntregado();
		this.distanciaRecorrida = mejorRuta.getDistanciaRecorrida();
		this.petroleoConsumido = mejorRuta.getPetroleoConsumido();
		
		return mejorRuta;
	}
	

	
	public Ruta busquedaLocal() {
		Ruta ruta = new Ruta(this.camion, this.fechaActual, this.horaActual);
		
		return ruta;	
	}
		
	
}
