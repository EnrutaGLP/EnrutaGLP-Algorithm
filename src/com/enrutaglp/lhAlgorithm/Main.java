package com.enrutaglp.lhAlgorithm;

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
		int posEstacionPrincipalX=0;
		int posEstacionPrincipalY=0;
		Astar astar = new Astar(70,50,pedidos,flota);
		//astar.calcularCaminoMasCorto(5,5,20,41);
		astar.resolverPedidos();
		//astar.pintarCamino();
		//astar.calcularCaminoMasCorto(15,15,20,49);
		astar.pintarCamino();
	}
}
