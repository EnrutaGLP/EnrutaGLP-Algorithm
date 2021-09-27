package com.enrutaglp.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.enrutaglp.model.Camion;
import com.enrutaglp.model.EntregaPedido;
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
				//childInd.mutate();
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
		
		Map<String, List<EntregaPedido>> entregasChild = new HashMap<String, List<EntregaPedido>>(); 
		
		
		int cantPedidos = ind1.getEntregas().size();
		
		int it = 0;
		
		for(String key: ind1.getEntregas().keySet()) {
			List<EntregaPedido> entregasPedido1 = ind1.getEntregas().get(key);
			List<EntregaPedido> entregasPedido2 = ind2.getEntregas().get(key);
			
			if(it<cantPedidos) {
				entregasChild.put(key, entregasPedido1);
			}
			else {
				entregasChild.put(key, entregasPedido2);
			}
			it ++;
			
			/*
			List<EntregaPedido> entregasPedido2 = ind2.getEntregas().get(key);
			
			Camion camion1 = entregasPedido1.get(0).getCamion();
			Camion camion2 = entregasPedido2.get(0).getCamion();
			
			List<EntregaPedido> entregasPedido1nuevo = new ArrayList<EntregaPedido>();
			List<EntregaPedido> entregasPedido2nuevo = new ArrayList<EntregaPedido>();
			
			for(int i=0;i<entregasPedido1.size();i++) {
				EntregaPedido entregaPedido1nuevo = entregasPedido1.get(i);
				entregaPedido1nuevo.setCamion(camion2);
				entregaPedido1nuevo.recalcularVariables();
				entregasPedido1nuevo.add(entregaPedido1nuevo);
			}
			
			for(int i=0;i<entregasPedido2.size();i++) {
				EntregaPedido entregaPedido2nuevo = entregasPedido2.get(i);
				entregaPedido2nuevo.setCamion(camion1);
				entregaPedido2nuevo.recalcularVariables();
				entregasPedido2nuevo.add(entregaPedido2nuevo);
			}
			
			ind1.set */
			
		}
			
		
		childInd.setEntregas(entregasChild);

		
		//Hacer crossover
		return childInd; 
	}
	
}
