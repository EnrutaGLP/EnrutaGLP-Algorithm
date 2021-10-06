package com.enrutaglp.algorithm;

import java.util.List;
import java.util.Map;

import com.enrutaglp.model.Bloqueo;
import com.enrutaglp.model.Camion;
import com.enrutaglp.model.Pedido;
import com.enrutaglp.model.TipoCamion;
import com.enrutaglp.utils.Utils;


public class Main {
	
	public static void main(String args[]) {
		Map<String,Pedido>pedidos = Utils.leerPedidos();
		pedidos = Utils.particionarPedidos(pedidos);
		List<TipoCamion>tiposCamiones = Utils.generarTiposCamiones();
		Map<String,Camion>flota = Utils.generarFlota(tiposCamiones,0,0);
		List<Bloqueo> bloqueos = Utils.leerBloqueos();
		Genetic genetic = new Genetic(10,50,pedidos,flota);
		genetic.run(50,50,1,1000,1000);
	}
}
