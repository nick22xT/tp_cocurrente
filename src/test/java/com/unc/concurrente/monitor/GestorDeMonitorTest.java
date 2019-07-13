package com.unc.concurrente.monitor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import com.unc.concurrente.mocks.RDPMock;
import com.unc.concurrente.mocks.RecursoMock;
import com.unc.concurrente.monitor.GestorDeMonitor;

public class GestorDeMonitorTest {
	
    private GestorDeMonitor monitor;
    
    @Before
    public void setUp() {
    	monitor = new GestorDeMonitor(RDPMock.getRDP());
    }
    
    @Test
    public void test_contar_sensibilizadas_se_espera_cero_como_resultado() {
    	Boolean[] vectorSensibilizadas = new Boolean[]{false, false, false, false, false};
    	
    	assertEquals(0, monitor.contarSensibilizadas(vectorSensibilizadas));
    }

    @Test
    public void test_contarSensibilizadas_se_espera_tres_como_resultado() {
    	Boolean[] vectorSensibilizadas = new Boolean[]{true, false, true, false, true};
    	
    	assertEquals(3, monitor.contarSensibilizadas(vectorSensibilizadas));
    }
    
    @Test
    public void test_getVectorDeColas_cuando_ningun_thread_entro_al_monitor() {
    	Boolean[] vectorDeColas = monitor.getVectorDeColas();
    	
    	assertFalse("Resultado no esperado en posicion 0", vectorDeColas[0]);
    	assertFalse("Resultado no esperado en posicion 1", vectorDeColas[1]);
    	assertFalse("Resultado no esperado en posicion 2", vectorDeColas[2]);
    	assertFalse("Resultado no esperado en posicion 3", vectorDeColas[3]);
    	assertFalse("Resultado no esperado en posicion 4", vectorDeColas[4]);
    	assertFalse("Resultado no esperado en posicion 5", vectorDeColas[5]);
    }
    
    @Test
    public void test_getVectorDeColas_se_espera_true_en_la_posicion_uno() {
    	Thread thread = new Thread(new RecursoMock(1, monitor));
    	thread.start();
    	
    	try {
			TimeUnit.MILLISECONDS.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    	Boolean[] vectorDeColas = monitor.getVectorDeColas();
    	
    	assertFalse("Resultado no esperado en posicion 0", vectorDeColas[0]);
    	assertTrue("Resultado no esperado en posicion 1", vectorDeColas[1]);
    	assertFalse("Resultado no esperado en posicion 2", vectorDeColas[2]);
    	assertFalse("Resultado no esperado en posicion 3", vectorDeColas[3]);
    	assertFalse("Resultado no esperado en posicion 4", vectorDeColas[4]);
    	assertFalse("Resultado no esperado en posicion 5", vectorDeColas[5]);
    }
}
