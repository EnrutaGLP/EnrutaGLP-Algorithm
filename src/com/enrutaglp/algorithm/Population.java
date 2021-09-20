package com.enrutaglp.algorithm;

import java.util.Vector;

import com.enrutaglp.model.Pedido;

public class Population {

	private Vector<Individual>individuals; 
	private int size;
	private int mu; 
	private int epsilon; 
	
	public Population(int mu, int epsilon) {
		this.mu = mu; 
		this.epsilon = epsilon;
	}
	
	public void generatePopulation() {
		for(int i=0; i<mu;i++) {
			Individual individual = new Individual();
		}
	}
	
	public boolean addIndividual(Individual individual) {
		individuals.add(individual);
		size++;
		return false; 
	}
	
	public Individual getBinaryTournament() {
		int place1, place2; 
		while(true) {
			place1 = (int)(Math.random()*size);
			place2 = (int)(Math.random()*size);
			if(place1!=place2)break; 
		}
		
		Individual ind1 = individuals.get(place1); 
		Individual ind2 = individuals.get(place2); 
		//return the one with the highest fitness 
		return ind1;
	}
	
	
}
