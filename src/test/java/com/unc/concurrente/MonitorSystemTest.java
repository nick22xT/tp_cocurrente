package com.unc.concurrente;


import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.unc.concurrente.Application;
import com.unc.concurrente.utils.XMLReader;

public class MonitorSystemTest {
	
	private static final String FILE_PATH = "src/test/resources/com/unc/concurrente/logs/transicionLog.txt";
	private static final List<String[]> INVARIANTES = XMLReader.leerInvatiantes("src/main/resources/PetriNetConfiguration.xml");
	private String[] arraySecuence;
	
	
	
	@Test
	public void verificarInvariantes() {
		this.iniciarMonitor();
		arraySecuence = this.leerArchivo().split("-");
		
		for(int i = 0;i < INVARIANTES.size(); i++) {
			assertTrue("No se cumple el T-invariante numero " + i, recorrerArreglo(INVARIANTES.get(i)));
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
	
	private boolean recorrerArreglo(String[] invariante) { // devuelve true si se encontro la secuencia
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
	
	private String leerArchivo() {
		FileReader file = null;
		BufferedReader buffer = null;
		String secuence = null;
		
		try {
			file = new FileReader(FILE_PATH);
			buffer = new BufferedReader(file);
			secuence = buffer.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return secuence;
	}

}
