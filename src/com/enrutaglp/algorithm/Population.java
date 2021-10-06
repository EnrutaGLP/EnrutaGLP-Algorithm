package com.enrutaglp.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
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
	private Map<String,Camion>flota;
	private double wA; 
	private double wB; 
	private double wC; 
	private int tournamentSize = 10;
	private double probability = 0.8;
	public Population(int mu, int epsilon, Map<String,Pedido>pedidos, Map<String,Camion>flota,
			double wA,double wB, double wC) {
		this.mu = mu; 
		this.epsilon = epsilon;
		this.wA = wA; 
		this.wB = wB; 
		this.wC = wC;
		this.best = null;
		this.flota = flota;
		generatePopulation(pedidos);
	}
	
	public void generatePopulation(Map<String,Pedido>pedidos) {
		this.individuals = new ArrayList<Individual>();
		for(int i=0; i<mu;i++) {
			Individual individual = new Individual(pedidos,flota);
			individuals.add(individual);
		}
		this.size = mu;
	}

	public void applySurvivorSelection() { 
		while(size>mu) {
			
			final int[] ints = new Random().ints(0, size).distinct().limit(tournamentSize).toArray();
			List<Individual> selectedForTournament = new ArrayList<Individual>();
			for(int i=0;i<tournamentSize;i++) {
				selectedForTournament.add(individuals.get(i));
			}
			Collections.sort(selectedForTournament);
			
			
		}
		
	}
	
	public boolean addIndividual(Individual individual) {
		individuals.add(individual);
		size++;
		if(size>(mu+epsilon)) {
			applySurvivorSelection();
		}
		if(best == null || best.getFitness() > individual.getFitness()) {
			best = individual;
			return true; 
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
		Individual ind3 = (ind1.calcularFitness(wA, wB, wC,flota)>ind2.calcularFitness(wA, wB, wC,flota) )? ind2 : ind1;
		return ind3;
	}

	public Individual getBest() {
		return best;
	}

	public void setBest(Individual best) {
		this.best = best;
	}
	
	
	
	
}
