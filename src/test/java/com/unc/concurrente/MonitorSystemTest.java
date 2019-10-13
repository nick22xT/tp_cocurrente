package com.unc.concurrente;


import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.unc.concurrente.Application;
import com.unc.concurrente.mocks.LogsFileReader;
import com.unc.concurrente.validators.PInvariantsValidator;
import com.unc.concurrente.validators.TInvariantsValidator;

public class MonitorSystemTest {
	
	private static final String T_FILE_PATH = "src/test/resources/com/unc/concurrente/logs/transicionLog.txt";
	private static final String P_FILE_PATH = "src/test/resources/com/unc/concurrente/logs/marcaLog.txt";
	private static final TInvariantsValidator VALIDADOR_T_INVARIANTES = new TInvariantsValidator("src/main/resources/PetriNetConfiguration.xml");
	private static final PInvariantsValidator VALIDADOR_P_INVARIANTES = new PInvariantsValidator("src/main/resources/PetriNetConfiguration.xml");
	private String[] transciciones;
	List<Integer[]> marcas;
	
	
	@Test
	public void verificarInvariantes() {
		this.iniciarMonitor();
		transciciones = LogsFileReader.leerLogTransiciones(T_FILE_PATH).split("-");
		marcas = LogsFileReader.leerLogEstados(P_FILE_PATH);
		
		assertTrue(VALIDADOR_P_INVARIANTES.validarPInvariantes(marcas));
		assertTrue(VALIDADOR_T_INVARIANTES.validarTInvariantes(transciciones));
	}
	
	private void iniciarMonitor() {
		try {
			Application.main(new String[] {"-d"});
			TimeUnit.SECONDS.sleep(40);
			Application.interrumpirMonitor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
