package com.enrutaglp.algorithm;

import java.util.List;
import java.util.Map;

import com.enrutaglp.model.Camion;
import com.enrutaglp.model.Pedido;
import com.enrutaglp.utils.Utils;

public class Genetic {

	Population population; 
	Map<String,Pedido>pedidos; 
	Map<String,Camion>flota; 
	int mapX; 
	int mapY; 
	
	public Genetic(int mapX, int mapY, Map<String,Pedido>pedidos, Map<String,Camion>flota) {
		this.mapX = mapX; 
		this.mapY = mapY; 
		this.pedidos = pedidos; 
		this.flota = flota; 
	}
	
	public void run(int maxIterNoImp, int numChildrenToGenerate, double wA, double wB, double wC) {
		
		int nbIterNoImp = 1; 
		Individual childInd; 
		population = new Population(50,20,pedidos,flota);
		boolean genNewBest; 
		for(int nbIter = 0; nbIterNoImp <= maxIterNoImp; nbIter++) {
			genNewBest = false;
			for(int i=0;i<numChildrenToGenerate;i++) {
				//Parent selection and crossover 
				childInd = crossover(population.getBinaryTournament(wA, wB, wC),population.getBinaryTournament(wA, wB, wC));
				//Apply mutation
				childInd.mutate();
				//Evaluate new individual
				boolean isNewBest = population.addIndividual(childInd);
				genNewBest = (isNewBest)? isNewBest:genNewBest;
			}
			Utils.printSolution(nbIter, population.getBest());
			
			if(genNewBest) nbIterNoImp = 1; 
			else nbIterNoImp++; 
		}
		
	}
	
	//Los 2 
	public Individual crossover(Individual ind1, Individual ind2) {
		Individual childInd = new Individual(); 
		
		
		
		//Hacer crossover
		return childInd; 
	}
	
}
