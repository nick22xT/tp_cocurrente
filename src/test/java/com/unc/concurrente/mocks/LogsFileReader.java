package com.unc.concurrente.mocks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogsFileReader {
	
	public static String leerLogTransiciones(String path) {
		FileReader file = null;
		BufferedReader buffer = null;
		String secuence = null;
		
		try {
			file = new FileReader(path);
			buffer = new BufferedReader(file);
			secuence = buffer.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return secuence;
	}
	
	public static List<Integer[]> leerLogEstados(String path) {
		FileReader file = null;
		BufferedReader buffer = null;
		List<String> secuencias = new ArrayList<>();
		
		try {
			file = new FileReader(path);
			buffer = new BufferedReader(file);
			
			while(buffer.readLine() != null) {
				if(buffer.readLine() != null) {
					secuencias.add(buffer.readLine());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return parserIntVector(secuencias);
	}
	
	private static List<Integer[]> parserIntVector(List<String> secuencias) {
		List<Integer[]> marcas = new ArrayList<>();
		
		for(int i = 0; i < secuencias.size(); i++) {
			String[] secuencia = secuencias.get(i).split(",");
			Integer[] numSecuencia = new Integer[secuencia.length];
			for(int j = 0; j < secuencia.length; j++) {
				numSecuencia[j] = Integer.parseInt(secuencia[j]);
			}
			marcas.add(numSecuencia);
		}
		return marcas;
		
	}

}
