package com.enrutaglp.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import com.enrutaglp.algorithm.Individual;
import com.enrutaglp.model.Bloqueo;
import com.enrutaglp.model.Camion;
import com.enrutaglp.model.Pedido;
import com.enrutaglp.model.Punto;
import com.enrutaglp.model.TipoCamion;

public class Utils {
	
	public static int maxCapacidadCamion = 25;

	public static Map<String,Pedido> leerPedidos() {
		Map<String,Pedido> pedidos = new HashMap<String,Pedido>();
		
		String filePath = new File("").getAbsolutePath().concat("\\parametros\\pedidos.txt"); 
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String line;
			int i = 0; 
			while((line = br.readLine()) != null) {
				String[]values = line.split(",");
				Pedido pedido = new Pedido(String.valueOf(i),values[0],Double.parseDouble(values[5]),Integer.parseInt(values[1]),
						Integer.parseInt(values[2]),values[3],values[4]);
				pedidos.put(pedido.getCodigo(), pedido);
				i++;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return pedidos;
	}
	public static Map<String,Pedido> leerPedidosParametro(String nombreArchivo){
		Map<String,Pedido> pedidos = new HashMap<String,Pedido>();
		
		String filePath = new File("").getAbsolutePath().concat("\\parametros\\").concat(nombreArchivo);
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String line;
			int i = 0; 
			while((line = br.readLine()) != null) {
				String[]values = line.split(",");
				Pedido pedido = new Pedido(String.valueOf(i),values[0],Double.parseDouble(values[5]),Integer.parseInt(values[1]),
						Integer.parseInt(values[2]),values[3],values[4]);
				pedidos.put(pedido.getCodigo(), pedido);
				i++;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return pedidos;
	}
	public static Map<String,Pedido> particionarPedidos(Map<String,Pedido>pedidos){
		
		return pedidos;
	}
	public static String generarCodigoCamion(String tara, int numUnidad) {
		DecimalFormat formatter = new DecimalFormat("00");
		return tara + formatter.format(numUnidad);
	}
	
	public static List<TipoCamion> generarTiposCamiones(){
		List<TipoCamion>tipos = new ArrayList<TipoCamion>();
		tipos.add(new TipoCamion("TA",2.5,25,12.5,25,50,2));
		tipos.add(new TipoCamion("TB",2.0,15,7.5,25,50,4));
		tipos.add(new TipoCamion("TC",1.5,10,5,25,50,4));
		tipos.add(new TipoCamion("TD",1.0,5,2.5,25,50,10));
		return tipos;
	}
	
	
	public static Map<String,Camion> generarFlota(List<TipoCamion>tipos,int plantaPrincipalX,int plantaPrincipalY){
		Map<String,Camion>flota = new HashMap<String,Camion>();
		for(int i=0;i<tipos.size();i++) {
			for(int j=0;j<tipos.get(i).getUnidades();j++) {
				String codigo = generarCodigoCamion(tipos.get(i).getTara(),j+1); 
				flota.put(codigo,new Camion(codigo,
						plantaPrincipalX,plantaPrincipalY,tipos.get(i).getCapacidadGLP(),
						tipos.get(i).getCapacidadTanque(),tipos.get(i)));
			}
		}
		return flota;
	}
	
	public static List<Bloqueo>leerBloqueos(){
		List<Bloqueo>bloqueos = new ArrayList<>(); 
		/*String filePath = new File("").getAbsolutePath().concat("\\parametros\\bloqueos.txt"); 
		String mes = "10";
		String anho = "2021";
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String line;
			int i = 0; 
			while((line = br.readLine()) != null) {
				String[]values = line.split(",");
				String[]fechas = values[0].split("-");
				int j = 2; 
				int orden = 1;
				if()
				Bloqueo bloqueo = new Bloqueo()
				while(j<values.length) {
					Punto punto = new Punto(Integer.parseInt(values[j]),
							Integer.parseInt(values[j+1]),orden);
					
					orden++;
					j+=2;
				}
				
				i++;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}*/
		return bloqueos; 
	}
	
	public static LocalDateTime getRandomDateTime(LocalDateTime lowerDateTime,LocalDateTime upperDateTime) {
		DateTimeFormatter datetimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		DecimalFormat decimalFormatter = new DecimalFormat("00");
		int randYear = ThreadLocalRandom.current().nextInt(lowerDateTime.getYear(), upperDateTime.getYear() + 1);
		int randMonth = ThreadLocalRandom.current().nextInt(lowerDateTime.getMonthValue(), upperDateTime.getMonthValue() + 1);
		int randDay = ThreadLocalRandom.current().nextInt(lowerDateTime.getDayOfMonth(), upperDateTime.getDayOfMonth() + 1);
		int randHour = ThreadLocalRandom.current().nextInt(lowerDateTime.getDayOfMonth(), upperDateTime.getDayOfMonth() + 1);
		
		return LocalDateTime.parse(decimalFormatter.format(randDay) + decimalFormatter.format(randMonth) + 
									decimalFormatter.format(randYear) + " " + decimalFormatter.format(randHour) + ":00",datetimeFormatter);
	}
	
	public static void printSolution(int nbIter,Individual individual,PrintWriter printWriter) {
		printWriter.println("Generacion " + nbIter + " Fitness: " + individual.getFitness());
		printWriter.println("------------------------------------------------------------\n");
		
		for(String camion : individual.getRutas().keySet()) {
			printWriter.print(camion + ": (cantidad de pedidos asignados " + individual.getAsignacionesCamiones().get(camion).size() + ")");
			for(Punto punto : individual.getRutas().get(camion).getNodos()) {
				printWriter.print("("+punto.getUbicacionX()+","+punto.getUbicacionY()+") ");
			}
			printWriter.println();
		}
		printWriter.println("------------------------------------------------------------\n");
	}
	
}
