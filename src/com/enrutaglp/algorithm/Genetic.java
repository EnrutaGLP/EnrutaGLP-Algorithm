package com.enrutaglp.algorithm;

public class Genetic {

	Population population; 
	int mapX; 
	int mapY; 
	
	public Genetic(int mapX, int mapY) {
		this.mapX = mapX; 
		this.mapY = mapY; 
	}
	
	public void run(int maxIterNoImp) {
		
		int nbIterNoImp = 1; 
		Individual childInd; 
		population = new Population(50,20);
		for(int nbIter = 0; nbIterNoImp <= maxIterNoImp; nbIter++) {
			
			//Parent selection and crossover 
			childInd = crossover(population.getBinaryTournament(),population.getBinaryTournament());
			//Evaluate new individual
			boolean isNewBest = population.addIndividual(childInd);
			
			if(isNewBest) nbIterNoImp = 1; 
			else nbIterNoImp++; 
		}
		
		
	}
	
	public Individual crossover(Individual ind1, Individual ind2) {
		Individual childInd = new Individual(); 
		//Hacer crossover
		return childInd; 
	}
	
}
