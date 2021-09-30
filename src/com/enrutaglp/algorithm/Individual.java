package com.enrutaglp.algorithm;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import com.enrutaglp.model.Camion;
import com.enrutaglp.model.EntregaPedido;
import com.enrutaglp.model.Pedido;
import com.enrutaglp.utils.Utils;

public class Individual {
	
	private Map<String, List<EntregaPedido>> entregas;
	private Map<String,Map<String,Integer>> chromosome;
	private List<Camion>camiones;
	private double consumoTotalPetroleo = 0; //suma de consumo de todas las entregas
	private byte seEstanEntregandoATiempo = 1; //1 si todos se entregan a tiempo, 0 si no 
	private int minutosAdicional = 0;  //suma de minutos en los que no se entregan a tiempo los pedido
	
	public Individual() {
		this.entregas = new HashMap<String, List<EntregaPedido>>();
		this.chromosome = new HashMap<String, Map<String,Integer>>();
	}
	
	public Individual(Map<String,Pedido>pedidos, Map<String,Camion>flota) {
		this.entregas = new HashMap<String, List<EntregaPedido>>();
		this.chromosome = new HashMap<String, Map<String,Integer>>();
		this.generateRandomIndividual(pedidos,flota);
	}
	
	
	public Map<String, List<EntregaPedido>> getEntregas() {
		return entregas;
	}

	public void setEntregas(Map<String, List<EntregaPedido>> entregas) {
		this.entregas = entregas;
	}

	public double getConsumoTotalPetroleo() {
		return consumoTotalPetroleo;
	}

	public void setConsumoTotalPetroleo(double consumoTotalPetroleo) {
		this.consumoTotalPetroleo = consumoTotalPetroleo;
	}

	public byte getSeEstanEntregandoATiempo() {
		return seEstanEntregandoATiempo;
	}

	public void setSeEstanEntregandoATiempo(byte seEstanEntregandoATiempo) {
		this.seEstanEntregandoATiempo = seEstanEntregandoATiempo;
	}

	public int getMinutosAdicional() {
		return minutosAdicional;
	}

	public void setMinutosAdicional(int minutosAdicional) {
		this.minutosAdicional = minutosAdicional;
	}

	
	public Map<String, Map<String, Integer>> getChromosome() {
		return chromosome;
	}

	public void setChromosome(Map<String, Map<String, Integer>> chromosome) {
		this.chromosome = chromosome;
	}

	public List<Camion> getCamiones() {
		return camiones;
	}

	public void setCamiones(List<Camion> camiones) {
		this.camiones = camiones;
	}

	public void insertarEntregaPedido(EntregaPedido entregaPedido) {
		
		List<EntregaPedido> entregasPedidos = this.entregas.get(entregaPedido.getPedido().getCodigo());
		
		if(entregasPedidos == null) {
			entregasPedidos = new ArrayList<EntregaPedido>();
			entregasPedidos.add(entregaPedido);
			this.entregas.put(entregaPedido.getPedido().getCodigo(), entregasPedidos);
		}
		else {
			this.entregas.get(entregaPedido.getPedido().getCodigo()).add(entregaPedido);
		}
	}
	
	public void generateRandomIndividual(Map<String,Pedido>pedidos, Map<String,Camion>flota) {
		List<Pedido> listaPedidos = pedidos.values().stream().collect(Collectors.toList());
		List<Camion> listaFlota = flota.values().stream().collect(Collectors.toList());
		
		for(int i=0;i<listaPedidos.size();i++) {
			String key = listaPedidos.get(i).getCodigo();
			int randomCamionIndex = ThreadLocalRandom.current().nextInt(0, listaFlota.size());
			Map<String,Integer> value = new HashMap<String, Integer>();
			value.put(listaFlota.get(randomCamionIndex).getCodigo(), 0);
			chromosome.put(key, value);
		}
	}
	
	public void addGene(String key, Map<String,Integer> value) {
		chromosome.put(key, value);
	}
	
	public int getSize() {
		return chromosome.keySet().size();
	}
	
	
	public double calcularFitness(double wA, double wB, double wC) {
		double fitness = 0.0; 
		//ejecuta el grasp
		
		return fitness; 
	}
	

	public double getFitness(double wA, double wB, double wC) {
		double fitness = wA*this.consumoTotalPetroleo + wB*(1-this.seEstanEntregandoATiempo) + wC*(1-this.seEstanEntregandoATiempo)*this.minutosAdicional;
		
		return fitness;  
		
	}
	
	
}
