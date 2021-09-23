package com.enrutaglp.algorithm;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import com.enrutaglp.model.Camion;
import com.enrutaglp.model.EntregaPedido;
import com.enrutaglp.model.Pedido;
import com.enrutaglp.utils.Utils;

public class Individual {
	
	private List<EntregaPedido>entregas;
	private double consumoTotalPetroleo; //suma de consumo de todas las entregas
	private byte seEstanEntregandoATiempo; //1 si todos se entregan a tiempo, 0 si no 
	private int minutosAdicional;  //suma de minutos en los que no se entregan a tiempo los pedido
	
	public Individual() {
		
	}
	
	public Individual(Map<String,Pedido>pedidos, Map<String,Camion>flota) {
		this.generateRandomIndividual(pedidos,flota);
	}
	
	public void generateRandomIndividual(Map<String,Pedido>pedidos, Map<String,Camion>flota) {
		List listaPedidos = pedidos.values().stream().collect(Collectors.toList());
		List listaFlota = flota.values().stream().collect(Collectors.toList());
		Collections.shuffle(listaPedidos,new Random());
		
		for(int i=0;i<pedidos.size();i++) {
			
			//Select a random Camion
			int randomCamionIndex = ThreadLocalRandom.current().nextInt(0, listaFlota.size());
			//Get a random localDateTime
			LocalDateTime randomDateTime = Utils.getRandomDateTime(LocalDateTime.now(), 
					((Pedido)listaPedidos.get(i)).getFechaHoraLimite()); 
			
			((Camion)listaFlota.get(randomCamionIndex)).verificarDisponibilidad(randomDateTime,
					(Pedido)listaPedidos.get(i));;
			
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