package com.unc.concurrente;


import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.unc.concurrente.Application;
import com.unc.concurrente.mocks.InvariantsFileReader;
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
		arraySecuence = InvariantsFileReader.leerTInvariantes(T_FILE_PATH).split("-");
		marcas = InvariantsFileReader.leerPInvariantes(P_FILE_PATH);
		
		
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
	
	
	
}
