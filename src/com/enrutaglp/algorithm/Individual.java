package com.enrutaglp.algorithm;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
	
	private Map<String, List<EntregaPedido>> entregas;
	private double consumoTotalPetroleo = 0; //suma de consumo de todas las entregas
	private byte seEstanEntregandoATiempo = 1; //1 si todos se entregan a tiempo, 0 si no 
	private int minutosAdicional = 0;  //suma de minutos en los que no se entregan a tiempo los pedido
	
	public Individual() {
		this.entregas = new HashMap<String, List<EntregaPedido>>();
	}
	
	public Individual(Map<String,Pedido>pedidos, Map<String,Camion>flota) {
		this.entregas = new HashMap<String, List<EntregaPedido>>();
		
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
		List listaPedidos = pedidos.values().stream().collect(Collectors.toList());
		List listaFlota = flota.values().stream().collect(Collectors.toList());
		Collections.shuffle(listaPedidos,new Random());
		
		for(int i=0;listaPedidos.size()!=0;i++) {
			EntregaPedido entregaPedido;
			do {
				//Select a random Camion
				int randomCamionIndex = ThreadLocalRandom.current().nextInt(0, listaFlota.size());
				//Get a random localDateTime
				LocalDateTime randomDateTime = Utils.getRandomDateTime(LocalDateTime.now(), 
						((Pedido)listaPedidos.get(i)).getFechaHoraLimite()); 
				entregaPedido = ((Camion)listaFlota.get(randomCamionIndex)).addPedido(randomDateTime,
						(Pedido)listaPedidos.get(i));
				
			}while(entregaPedido==null);
			
		}
	}
	
	//Los 2
	public void mutate() {
		
	}
	
	//Stev
	public double calcularFitness(double wA, double wB, double wC) {
		double fitness = 0.0; 
		
		
		for(int i=0;i<this.entregas.size();i++) {
			//consumo total petroleo
			EntregaPedido entregaPedido = this.entregas.get(i);
			this.consumoTotalPetroleo += entregaPedido.getConsumoPetroleo();
			
			Duration duration = Duration.between(entregaPedido.getPedido().getFechaHoraLimite(), entregaPedido.getHoraEntregada());
			
			if(duration.toMinutes()>0) {
				this.minutosAdicional += duration.toMinutes();
				this.seEstanEntregandoATiempo = 0;
			}
			
		}
		
		fitness = wA*this.consumoTotalPetroleo + wB*(1-this.seEstanEntregandoATiempo) + wC*(1-this.seEstanEntregandoATiempo)*this.minutosAdicional;
		
		return fitness; 
	}
	

	//Stev
	public double getFitness(double wA, double wB, double wC) {
		double fitness = wA*this.consumoTotalPetroleo + wB*(1-this.seEstanEntregandoATiempo) + wC*(1-this.seEstanEntregandoATiempo)*this.minutosAdicional;
		
		return fitness;  
		
	}
	
	
}
