package com.enrutaglp.astarAlgorithm;

import java.util.List;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.enrutaglp.model.Camion;
import com.enrutaglp.model.Pedido;
import com.enrutaglp.model.TipoCamion;
import com.enrutaglp.model.Planta;
import com.enrutaglp.utils.Utils;
	


public class Main {
	
	public static void main(String args[]) throws IOException {
		Map<String,Pedido>pedidos = null;
		List<TipoCamion>tiposCamiones = Utils.generarTiposCamiones();
		Map<String,Camion>flota = Utils.generarFlota(tiposCamiones,0,0);
		String fechaIni="12/09/2021";
		String horaIni="20:00";		
		
		String filePathMuestra10 = new File("").getAbsolutePath().concat("\\parametros\\resultadosMuestra10.csv");
		String filePathMuestra100 = new File("").getAbsolutePath().concat("\\parametros\\resultadosMuestra100.csv");
		String filePathMuestra200 = new File("").getAbsolutePath().concat("\\parametros\\resultadosMuestra200.csv");
		String nombreArchivo10="muestra10_";
		String nombreArchivo100="muestra100_";
		String nombreArchivo200="muestra200_";
		String nombreArchivoPedidos;
		
		DateTimeFormatter formato=DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime fechaIniFormato=LocalDateTime.parse(fechaIni+" "+horaIni,formato);
		Planta planta= new Planta(12,8,1000,1000,1000,1000,true);
		Astar astar = new Astar(70,70,pedidos,flota, fechaIniFormato, planta);
		astar.hallarCamionesImportantes();
		
		int i=1;
		String respuesta;

		try {
			File statText=new File(filePathMuestra10);
			FileOutputStream is;
			is = new FileOutputStream(statText);
			OutputStreamWriter osw = new OutputStreamWriter(is);
			Writer w=new BufferedWriter(osw);
			while(i<=3) {					
				nombreArchivoPedidos=nombreArchivo10.concat(String.valueOf(i)).concat(".txt");
				System.out.println(nombreArchivoPedidos);
				pedidos=Utils.leerPedidosParametro(nombreArchivoPedidos);
				astar.setPedidos(pedidos);
				respuesta=astar.resolverPedidos();
				System.out.print(respuesta);
				w.write(respuesta);
				i++;
			}
			w.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error al crear archivor reporte muestra 10");
			return;
		}
		i=1;
		/*
		try {
			File statText2=new File(filePathMuestra100);
			FileOutputStream is2;
			is2 = new FileOutputStream(statText2);
			OutputStreamWriter osw2 = new OutputStreamWriter(is2);
			Writer w2=new BufferedWriter(osw2);
			while(i<=50) {					
				nombreArchivoPedidos=nombreArchivo100.concat(String.valueOf(i)).concat(".txt");
				pedidos=Utils.leerPedidosParametro(nombreArchivoPedidos);
				astar.setPedidos(pedidos);
				respuesta=astar.resolverPedidos();
				w2.write(respuesta);
				i++;
			}
			w2.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error al crear archivor reporte muestra 100");
			return;
		}
		i=1;
		try {
			File statText3=new File(filePathMuestra200);
			FileOutputStream is3;
			is3 = new FileOutputStream(statText3);
			OutputStreamWriter osw3 = new OutputStreamWriter(is3);
			Writer w3=new BufferedWriter(osw3);
			while(i<=50) {					
				nombreArchivoPedidos=nombreArchivo200.concat(String.valueOf(i)).concat(".txt");
				pedidos=Utils.leerPedidosParametro(nombreArchivoPedidos);
				astar.setPedidos(pedidos);
				respuesta=astar.resolverPedidos();
				w3.write(respuesta);
				i++;
			}
			w3.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error al crear archivor reporte muestra 200");
			return;
		}
		*/
		
		/*astar.calcularCaminoMasCorto(5,5,20,41);
		astar.pintarCamino();
		astar.resetearMapa();
		astar.calcularCaminoMasCorto(15,15,20,45);
		astar.pintarCamino();
		astar.resetearMapa();*/
		
	}
}
