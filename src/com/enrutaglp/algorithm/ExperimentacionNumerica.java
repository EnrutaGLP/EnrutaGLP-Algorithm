package com.enrutaglp.algorithm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import com.enrutaglp.model.Camion;
import com.enrutaglp.model.Pedido;
import com.enrutaglp.model.TipoCamion;
import com.enrutaglp.utils.Utils;

public class ExperimentacionNumerica {
	public static void main(String args[]) {

		Map<String,Pedido>pedidos = null;
		List<TipoCamion>tiposCamiones = Utils.generarTiposCamiones();
		Map<String,Camion>flota = Utils.generarFlota(tiposCamiones,0,0);
		
		String filePathMuestra10 = new File("").getAbsolutePath().concat("//resultadosExpNum//genetico//agen_resultadosMuestra10.csv");
		String filePathMuestra100 = new File("").getAbsolutePath().concat("//resultadosExpNum//genetico//agen_resultadosMuestra100.csv");
		String filePathMuestra200 = new File("").getAbsolutePath().concat("//resultadosExpNum//genetico//agen_resultadosMuestra200.csv");
		String nombreArchivo10="muestra10_";
		String nombreArchivo100="muestra100_";
		String nombreArchivo200="muestra200_";
		String nombreArchivoPedidos;
		int dimMapX = 70; 
		int dimMapY = 50; 
		int maxIterNoImp = 5; 
		int numChildrenToGenerate = 2;
		double wA = 1; 
		double wB = 1000;
		double wC = 1000; 
		
		//assert wC>1000 : "This is not the value";
		
		/*try {
			File statText=new File(filePathMuestra10);
			FileOutputStream is;
			is = new FileOutputStream(statText);
			OutputStreamWriter osw = new OutputStreamWriter(is);
			Writer w=new BufferedWriter(osw);
			for(int i=1;i<51;i++) {
				System.out.println("Muestra 10 - Prueba Numero " + i);
				nombreArchivoPedidos=nombreArchivo10.concat(String.valueOf(i)).concat(".txt");
				pedidos=Utils.leerPedidosParametro(nombreArchivoPedidos);
				Genetic genetic = new Genetic(dimMapX,dimMapY,pedidos,flota);
				String nombreArchivo = "reporte_10_" + i + ".txt"; 
				Individual bestSolution = genetic.run(maxIterNoImp,numChildrenToGenerate,wA,wB,wC,nombreArchivo);
				String outputString = String.valueOf(bestSolution.getConsumoTotalPetroleo()) + ";" + String.valueOf(bestSolution.getCantidadGlpNoEntregado()) + 
						";" + String.valueOf(Global.CantidadTotalGlp) + ";" + String.valueOf(bestSolution.getCantidadPedidosNoEntregados()) 
						+";" + String.valueOf(pedidos.size()) + "\n";
				w.write(outputString);
			}
			w.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error al crear archivor reporte muestra 10");
			return;
		}*/
		/*
		try {
			File statText=new File(filePathMuestra100);
			FileOutputStream is;
			is = new FileOutputStream(statText);
			OutputStreamWriter osw = new OutputStreamWriter(is);
			Writer w=new BufferedWriter(osw);
			for(int i=16;i<51;i++) {
				System.out.println("Muestra 100 - Prueba Numero " + i);
				nombreArchivoPedidos=nombreArchivo100.concat(String.valueOf(i)).concat(".txt");
				pedidos=Utils.leerPedidosParametro(nombreArchivoPedidos);
								
				Genetic genetic = new Genetic(dimMapX,dimMapY,pedidos,flota);
				
				String nombreArchivo = "reporte_100_" + i + ".txt"; 
				Individual bestSolution = genetic.run(maxIterNoImp,numChildrenToGenerate,wA,wB,wC,nombreArchivo);
				
				String outputString = String.valueOf(bestSolution.getConsumoTotalPetroleo()) + ";" + String.valueOf(bestSolution.getCantidadGlpNoEntregado()) + 
						";" + String.valueOf(Global.CantidadTotalGlp) + ";" + String.valueOf(bestSolution.getCantidadPedidosNoEntregados()) 
						+";" + String.valueOf(pedidos.size()) + "\n";
				System.out.println(outputString);
				w.write(outputString);
			}
			w.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error al crear archivor reporte muestra 100");
			return;
		}
		*/
		///*
		try {
			File statText=new File(filePathMuestra200);
			FileOutputStream is;
			is = new FileOutputStream(statText);
			OutputStreamWriter osw = new OutputStreamWriter(is);
			Writer w=new BufferedWriter(osw);
			for(int i=47;i<51;i++) {
				System.out.println("Muestra 200 - Prueba Numero " + i);
				nombreArchivoPedidos=nombreArchivo200.concat(String.valueOf(i)).concat(".txt");
				pedidos=Utils.leerPedidosParametro(nombreArchivoPedidos);
				Genetic genetic = new Genetic(dimMapX,dimMapY,pedidos,flota);
				String nombreArchivo = "reporte_200_" + i + ".txt"; 
				Individual bestSolution = genetic.run(maxIterNoImp,numChildrenToGenerate,wA,wB,wC,nombreArchivo);
				String outputString = String.valueOf(bestSolution.getConsumoTotalPetroleo()) + ";" + String.valueOf(bestSolution.getCantidadGlpNoEntregado()) + 
						";" + String.valueOf(Global.CantidadTotalGlp) + ";" + String.valueOf(bestSolution.getCantidadPedidosNoEntregados()) 
						+";" + String.valueOf(pedidos.size()) + "\n";
				System.out.println(outputString);
				w.write(outputString);
			}
			w.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error al crear archivor reporte muestra 200");
			return;
		}//*/
		
	}
}
