package com.enrutaglp.algorithm;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
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
	
	public Individual run(int maxIterNoImp, int numChildrenToGenerate, double wA, double wB, double wC, String nombreArchivo) {
		FileWriter fileWriter = null; 
		PrintWriter printWriter = null;
		try {
			String nombreCompletoArchivo= new File("").getAbsolutePath().concat("\\reportesAG\\" +nombreArchivo);
			fileWriter = new FileWriter(nombreCompletoArchivo);
		    printWriter = new PrintWriter(fileWriter);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		int nbIterNoImp = 1; 
		double percentGenesToMutate = 0.3;
		Individual childInd1,childInd2; 
		//Initialize population
		population = new Population(10,20,pedidos,flota,wA,wB,wC);
		boolean genNewBest; 
		for(int nbIter = 0; nbIterNoImp <= maxIterNoImp; nbIter++) {
 			//System.out.println("Generacion " + nbIter);
			genNewBest = false;
			for(int i=0;i<numChildrenToGenerate;i++) {
				//Parent selection and crossover 
				childInd1 = crossover(population.getBinaryTournament(wA, wB, wC),population.getBinaryTournament(wA, wB, wC));
				//Apply mutation
				childInd2 = mutate(childInd1,percentGenesToMutate);
				//Evaluate new individuals
				childInd1.calcularFitness(wA, wB, wC,flota);
				childInd2.calcularFitness(wA, wB, wC,flota);
				boolean isNewBest = population.addIndividual(childInd1) || population.addIndividual(childInd2);
				genNewBest = (isNewBest)? isNewBest:genNewBest;
			}
			//Utils.printSolution(nbIter, population.getBest(),printWriter);
			if(genNewBest) nbIterNoImp = 1; 
			else nbIterNoImp++; 
		}
	    printWriter.close();
	    return population.getBest();
	}
	
	public Individual crossover(Individual ind1, Individual ind2) {
		Individual childInd = new Individual(); 
		
		for(int i=0;i<listaPedidos.size();i++) {
			String codigo = listaPedidos.get(i).getCodigo();
			int choice = ThreadLocalRandom.current().nextInt(0, 2);
			if(choice==0) {
				childInd.addGene(codigo,ind1.getChromosome().get(codigo),listaPedidos.get(i));
			} else if(choice == 1) {
				childInd.addGene(codigo,ind2.getChromosome().get(codigo),listaPedidos.get(i));
			}
		}
		
		return childInd; 
	}
	
	public Individual mutate(Individual individual, double percentGenesToMutate) {
		Individual mutatedInd = new Individual();
		
		for(int i=0;i<listaPedidos.size();i++) {
			String codigo = listaPedidos.get(i).getCodigo();
			mutatedInd.addGene(codigo, individual.getChromosome().get(codigo),listaPedidos.get(i));
		}
		int numGenesToChange = (int)(listaPedidos.size()*percentGenesToMutate);
		for(int i=0;i<numGenesToChange;i++) {
			String pedido1 = listaPedidos.get(ThreadLocalRandom.current().nextInt(0, listaPedidos.size()))
					.getCodigo();
			String pedido2 = listaPedidos.get(ThreadLocalRandom.current().nextInt(0, listaPedidos.size()))
					.getCodigo();
			mutatedInd.swap(pedido1,pedido2);
		}
		return mutatedInd;
	}
	
}
