package com.enrutaglp.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

import com.enrutaglp.model.Camion;
import com.enrutaglp.model.Pedido;

public class Population {

	private List<Individual>individuals; 
	private Individual best;
	private int size;
	private int mu; 
	private int epsilon; 
	
	public Population(int mu, int epsilon, Map<String,Pedido>pedidos, Map<String,Camion>flota) {
		this.mu = mu; 
		this.epsilon = epsilon;
		generatePopulation(pedidos,flota);
	}
	
	public void generatePopulation(Map<String,Pedido>pedidos, Map<String,Camion>flota) {
		this.individuals = new ArrayList<Individual>();
		for(int i=0; i<mu;i++) {
			Individual individual = new Individual(pedidos,flota);
			individuals.add(individual);
		}
		this.size = mu;
	}

	public void applySurvivorSelection() {
		
	}
	
	public boolean addIndividual(Individual individual) {
		individuals.add(individual);
		size++;
		if(size>(mu+epsilon)) {
			applySurvivorSelection();
		}
		return false; 
	}
	
	public Individual getBinaryTournament(double wA, double wB, double wC) {
		int place1, place2; 
		while(true) {
			place1 = ThreadLocalRandom.current().nextInt(0, size);
			place2 = ThreadLocalRandom.current().nextInt(0, size);
			if(place1!=place2)break; 
		}
		
		Individual ind1 = individuals.get(place1); 
		Individual ind2 = individuals.get(place2); 
		//return the one with the lowest fitness 
		Individual ind3 = (ind1.calcularFitness(wA, wB, wC)>ind2.calcularFitness(wA, wB, wC) )? ind2 : ind1;
		return ind3;
	}

	public Individual getBest() {
		return best;
	}

	public void setBest(Individual best) {
		this.best = best;
	}
	
	
	
	
}
