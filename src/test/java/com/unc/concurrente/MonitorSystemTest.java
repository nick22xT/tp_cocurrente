package com.unc.concurrente;


import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.unc.concurrente.Application;
import com.unc.concurrente.mocks.PInvariante;
import com.unc.concurrente.utils.XMLReader;

public class MonitorSystemTest {
	
	private static final String T_FILE_PATH = "src/test/resources/com/unc/concurrente/logs/transicionLog.txt";
	private static final String P_FILE_PATH = "src/test/resources/com/unc/concurrente/logs/marcaLog.txt";
	private static final List<String[]> T_INVARIANTES = XMLReader.leerTInvatiantes("src/main/resources/PetriNetConfiguration.xml");
	private static final List<PInvariante> P_INVARIANTES = XMLReader.leerPInvariantes("src/main/resources/PetriNetConfiguration.xml");
	private String[] arraySecuence;
	List<Integer[]> marcas;
	
	
	@Test
	public void verificarInvariantes() {
		this.iniciarMonitor();
		arraySecuence = this.leerTInvariantes(T_FILE_PATH).split("-");
		marcas = this.leerPInvariantes(P_FILE_PATH);
		
		
		for(int i = 0; i < T_INVARIANTES.size(); i++) {
			assertTrue("No se cumple el T-invariante numero " + i, recorrerTInvariantes(T_INVARIANTES.get(i)));
		}
		
		for(int i = 0; i < P_INVARIANTES.size(); i++) {
			assertTrue("No se cumple el P-invariante numero " + i, recorrerPInvariantes(P_INVARIANTES.get(i)));
		}
	}
	
	private void iniciarMonitor() {
		try {
			Application.main(new String[] {"-d"});
			TimeUnit.SECONDS.sleep(100);
			Application.interrumpirMonitor();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private boolean recorrerTInvariantes(String[] invariante) { // devuelve true si se encontro la secuencia
		int cursor = 0;

		for(int i = 0; i < arraySecuence.length; i++) {
			if(invariante[cursor].equals(arraySecuence[i])) {
				cursor++;
				arraySecuence[i] = "";
				if(cursor == invariante.length) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean recorrerPInvariantes(PInvariante invariante) { // devuelve true si se encontro la secuencia
		int suma = 0;

		for(int i = 0; i < marcas.size(); i++) {
			Integer[] marca = marcas.get(i);
			String[] plazas = invariante.getSecuencia();
			
			for(int j= 0; j < plazas.length; j++) {
				suma += marca[Integer.parseInt(plazas[j])];
			}
			if(suma != invariante.getTotal()) {
				return false;
			}
			
			suma = 0;
		}
		return true;
	}
	
	private String leerTInvariantes(String path) {
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
	
	private List<Integer[]> leerPInvariantes(String path) {
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
	
	private List<Integer[]> parserIntVector(List<String> secuencias) {
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
