package com.enrutaglp.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.enrutaglp.model.Pedido;

public class Utils {
	
	public static List<Pedido> leerPedidos() {
		List<Pedido> pedidos = new ArrayList<Pedido>();
		
		String filePath = new File("").getAbsolutePath().concat("\\parametros\\pedidos.txt"); 
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String line;
			while((line = br.readLine()) != null) {
				String[]values = line.split(",");
				Pedido pedido = new Pedido(values[0],Double.parseDouble(values[5]),Integer.parseInt(values[1]),
						Integer.parseInt(values[2]),values[3],values[4]);
				pedidos.add(pedido);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return pedidos;
	}
	
}
