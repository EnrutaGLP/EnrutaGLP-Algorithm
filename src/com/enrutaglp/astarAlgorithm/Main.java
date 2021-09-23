package com.enrutaglp.algorithmstar;

import java.util.List;
import java.util.Map;
import com.enrutaglp.model.Camion;
import com.enrutaglp.model.Pedido;
import com.enrutaglp.model.TipoCamion;
import com.enrutaglp.utils.Utils;


public class Main {
	
	public static void main(String args[]) {
		Map<String,Pedido>pedidos = Utils.leerPedidos();
		List<TipoCamion>tiposCamiones = Utils.generarTiposCamiones();
		Map<String,Camion>flota = Utils.generarFlota(tiposCamiones,0,0);
		Astar astar = new Astar(70,50,pedidos,flota);
		astar.run(50,1);
	}
}
