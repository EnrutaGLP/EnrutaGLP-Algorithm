package com.enrutaglp.algorithm;

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
		Individual childInd1,childInd2; 
		
		population = new Population(50,20,pedidos,flota);
		boolean genNewBest; 
		for(int nbIter = 0; nbIterNoImp <= maxIterNoImp; nbIter++) {
			genNewBest = false;
			for(int i=0;i<numChildrenToGenerate;i++) {
				//Parent selection and crossover 
				childInd1 = crossover(population.getBinaryTournament(wA, wB, wC),population.getBinaryTournament(wA, wB, wC));
				//Apply mutation
				childInd2 = childInd1.mutate();
				//Evaluate new individuals
				childInd1.calcularFitness(wA, wB, wC);
				childInd2.calcularFitness(wA, wB, wC);
				
				boolean isNewBest = population.addIndividual(childInd1) || population.addIndividual(childInd2);
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
