package com.enrutaglp.algorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.JSONParser;

import com.enrutaglp.model.Pedido;
import com.enrutaglp.utils.Utils;


public class Main {
	
	public static void main(String args[]){
		//Leer parametros
		//leerParametros();
		List<Pedido>pedidos = Utils.leerPedidos();
		Genetic genetic = new Genetic(70,50);
		genetic.run(50);
	}
}
