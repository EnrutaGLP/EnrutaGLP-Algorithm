package com.enrutaglp.algorithm.grasp;

import java.util.List;
import java.util.Map;

import com.enrutaglp.algorithm.Genetic;
import com.enrutaglp.model.Bloqueo;
import com.enrutaglp.model.Camion;
import com.enrutaglp.model.Pedido;
import com.enrutaglp.model.TipoCamion;
import com.enrutaglp.utils.Utils;

public class Main {
	
	public static void main(String args[]) {
		System.out.println("grasp is running");
		Map<String,Pedido>pedidos = Utils.leerPedidos();
		pedidos = Utils.particionarPedidos(pedidos);
		List<TipoCamion>tiposCamiones = Utils.generarTiposCamiones();
		Map<String,Camion>flota = Utils.generarFlota(tiposCamiones,0,0);
		//cambiar esta cosa
		Grasp grasp = new Grasp(pedidos,flota.get("TA01"),"12/09/2021", "20:00",1,1000,1000);
		Ruta ruta = grasp.run(10);
	}
}
