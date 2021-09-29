package com.enrutaglp.algorithm;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import com.enrutaglp.model.Camion;
import com.enrutaglp.model.Pedido;
import com.enrutaglp.utils.Utils;

public class Genetic {

	Population population; 
	Map<String,Pedido>pedidos; 
	List<Pedido> listaPedidos;
	Map<String,Camion>flota; 
	int mapX; 
	int mapY; 
	
	public Genetic(int mapX, int mapY, Map<String,Pedido>pedidos, Map<String,Camion>flota) {
		this.mapX = mapX; 
		this.mapY = mapY; 
		this.pedidos = pedidos; 
		this.listaPedidos = pedidos.values().stream().collect(Collectors.toList());
		this.flota = flota; 
	}
	
	public void run(int maxIterNoImp, int numChildrenToGenerate, double wA, double wB, double wC) {
		
		int nbIterNoImp = 1; 
		double percentGenesToMutate = 0.3;
		Individual childInd1,childInd2; 
		population = new Population(10,20,pedidos,flota);
		boolean genNewBest; 
		for(int nbIter = 0; nbIterNoImp <= maxIterNoImp; nbIter++) {
			genNewBest = false;
			for(int i=0;i<numChildrenToGenerate;i++) {
				//Parent selection and crossover 
				childInd1 = crossover(population.getBinaryTournament(wA, wB, wC),population.getBinaryTournament(wA, wB, wC));
				//Apply mutation
				childInd2 = mutate(childInd1,percentGenesToMutate);
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
	
	public Individual crossover(Individual ind1, Individual ind2) {
		Individual childInd = new Individual(); 
		
		for(int i=0;i<listaPedidos.size();i++) {
			String codigo = listaPedidos.get(i).getCodigo();
			int choice = ThreadLocalRandom.current().nextInt(0, 2);
			if(choice==0) {
				childInd.addGene(codigo,ind1.getChromosome().get(codigo));
			} else if(choice == 1) {
				childInd.addGene(codigo,ind2.getChromosome().get(codigo));
			}
		}
		
		return childInd; 
	}
	
	public Individual mutate(Individual individual, double percentGenesToMutate) {
		Individual mutatedInd = new Individual();
		
		for(int i=0;i<listaPedidos.size();i++) {
			String codigo = listaPedidos.get(i).getCodigo();
			mutatedInd.addGene(codigo, individual.getChromosome().get(codigo));
		}
		int numGenesToChange = (int)(listaPedidos.size()*percentGenesToMutate);
		for(int i=0;i<numGenesToChange;i++) {
			String pedido1 = listaPedidos.get(ThreadLocalRandom.current().nextInt(0, listaPedidos.size()))
					.getCodigo();
			String pedido2 = listaPedidos.get(ThreadLocalRandom.current().nextInt(0, listaPedidos.size()))
					.getCodigo();
			Map<String,Integer> valueAux = mutatedInd.getChromosome().get(pedido1);
			mutatedInd.getChromosome().replace(pedido1, mutatedInd.getChromosome().get(pedido2));
			mutatedInd.getChromosome().replace(pedido2,valueAux);
		}
		return mutatedInd;
	}
	
}
