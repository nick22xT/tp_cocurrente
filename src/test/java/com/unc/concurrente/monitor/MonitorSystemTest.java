package com.unc.concurrente.monitor;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.unc.concurrente.Application;

public class MonitorSystemTest {
	
	private static final String[] T_INVARIANTE_UNO={"0","3","6","8","10","12","14","16","18"};
	private static final String[] T_INVARIANTE_DOS={"0","3","7","9","11","13","15","16","18"};
	private static final String FILE_PATH = "D:\\2do Semestre 2018\\Programacion Concurrente\\Trabajo Final\\Tabajo Final Monitor\\src\\main\\resources\\transicionLog.txt";
	private String[] arraySecuence;
	
	
	
	@Test
	public void verificarInvariantes() {
		this.iniciarMonitor();
		
		assertTrue(recorrerArreglo(T_INVARIANTE_UNO));
		assertTrue(recorrerArreglo(T_INVARIANTE_DOS)); 
	}
	
	private void iniciarMonitor() {
		try {
			Application.main(null);
			TimeUnit.SECONDS.sleep(300);
			Application.interrumpirMonitor();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private boolean recorrerArreglo(String[] array) { // devuelve true si se encontro la secuencia
		int cursor = 0;
		arraySecuence = this.leerArchivo().split("-");

		for(int i = 0; i < arraySecuence.length; i++) {
			if(arraySecuence[i].equals(array[cursor])) {
				cursor++;
				if(cursor == array.length) {
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
