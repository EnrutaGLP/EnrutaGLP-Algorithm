package com.enrutaglp.algorithm;

import java.util.Map;
import java.util.Vector;

public class Individual {
	
	
	private CostSol myCostSol;
	private Vector<Integer> chromT;
	private Vector<Vector<Integer>> chromR;
	private Vector<Integer> succesors;
	private Vector<Integer> predecessors;
	private Map<Individual, Double> indivsPerProximity;
	private boolean isFeasible;
	private double biasedFitness;
	
	public Individual() {
		
	}
	
	public Individual(int nbClients, int nbVehicles) {
		this.succesors = new Vector<Integer>(nbClients + 1);
		this.predecessors = new Vector<Integer>(nbClients + 1);
		this.chromR = new Vector<Vector<Integer>>(nbVehicles);
		this.chromT = new Vector<Integer>(nbClients);
		
		
		
	}
	
}
