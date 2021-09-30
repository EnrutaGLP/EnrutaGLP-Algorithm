package com.enrutaglp.astarAlgorithm;

import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.enrutaglp.model.Camion;
import com.enrutaglp.model.Pedido;
import com.enrutaglp.model.TipoCamion;
import com.enrutaglp.model.Planta;
import com.enrutaglp.utils.Utils;
	


public class Main {
	
	public static void main(String args[]) {
		Map<String,Pedido>pedidos = Utils.leerPedidos();
		List<TipoCamion>tiposCamiones = Utils.generarTiposCamiones();
		Map<String,Camion>flota = Utils.generarFlota(tiposCamiones,0,0);
		String fechaIni="12/09/2021";
		String horaIni="00:00";
		DateTimeFormatter formato=DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime fechaIniFormato=LocalDateTime.parse(fechaIni+" "+horaIni,formato);
		Planta planta= new Planta(0,0,1000,1000,1000,1000,true);
		Astar astar = new Astar(70,70,pedidos,flota, fechaIniFormato, planta);
		astar.resolverPedidos();
		/*astar.calcularCaminoMasCorto(5,5,20,41);
		astar.pintarCamino();
		astar.resetearMapa();
		astar.calcularCaminoMasCorto(15,15,20,45);
		astar.pintarCamino();
		astar.resetearMapa();*/
	}
}
