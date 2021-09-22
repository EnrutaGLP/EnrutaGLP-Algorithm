package com.enrutaglp.algorithm;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Vector;
import java.util.stream.Collectors;

import com.enrutaglp.model.EntregaPedido;
import com.enrutaglp.model.Pedido;

public class Individual {
	
	private List<EntregaPedido>entregas;
	private double consumoTotalPetroleo; //suma de consumo de todas las entregas
	private byte seEstanEntregandoATiempo; //1 si todos se entregan a tiempo, 0 si no 
	private int minutosAdicional;  //suma de minutos en los que no se entregan a tiempo los pedido
	
	public Individual() {
		
	}
	
	public Individual(Map<String,Pedido>pedidos) {
		this.generateRandomIndividual(pedidos);
	}
	
	public void generateRandomIndividual(Map<String,Pedido>pedidos) {
		List listaPedidos = pedidos.values().stream().collect(Collectors.toList());
		Collections.shuffle(listaPedidos,new Random());
		for(int i=0;i<pedidos.size();i++) {
			
		}
	}
	
	//Los 2
	public void mutate() {
		
	}
	
	//Stev
	public double calcularFitness() {
		double fitness = 0.0; 
		return fitness; 
	}
	
	//Stev
	public double getFitness() {
		double fitness = 0.0; 
		return fitness; 
	}
}
