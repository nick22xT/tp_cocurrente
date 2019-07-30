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
    
    @Test
    public void test_operacionAnd_se_espera_true_en_la_posicion_cero_y_uno() {
    	Boolean[] vector1 = new Boolean[]{true, true, false, true, false, true};
    	Boolean[] vector2 = new Boolean[]{true, true, false, false, true, false};
    	
    	Boolean[] resultado = monitor.operacionAnd(vector1, vector2);
    	
    	assertTrue("Resultado no esperado en posicion 0", resultado[0]);
    	assertTrue("Resultado no esperado en posicion 1", resultado[1]);
    	assertFalse("Resultado no esperado en posicion 2", resultado[2]);
    	assertFalse("Resultado no esperado en posicion 3", resultado[3]);
    	assertFalse("Resultado no esperado en posicion 4", resultado[4]);
    	assertFalse("Resultado no esperado en posicion 5", resultado[5]);
    }
    
    @Test
    public void test_operacionAnd_se_espera_false_en_todas_las_posiciones() {
    	Boolean[] vector1 = new Boolean[]{false, false, false, true, false, true};
    	Boolean[] vector2 = new Boolean[]{true, true, false, false, true, false};
    	
    	Boolean[] resultado = monitor.operacionAnd(vector1, vector2);
    	
    	assertFalse("Resultado no esperado en posicion 0", resultado[0]);
    	assertFalse("Resultado no esperado en posicion 1", resultado[1]);
    	assertFalse("Resultado no esperado en posicion 2", resultado[2]);
    	assertFalse("Resultado no esperado en posicion 3", resultado[3]);
    	assertFalse("Resultado no esperado en posicion 4", resultado[4]);
    	assertFalse("Resultado no esperado en posicion 5", resultado[5]);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void test_operacionAnd_cuando_uno_de_los_vectores_no_tiene_el_largo_igual_al_numero_de_transiciones() {
    	Boolean[] vectorUno = new Boolean[]{false, false, false, true, false, true};
    	Boolean[] vectorDos = new Boolean[]{true, true, false, false, true, false, false};
    	
    	monitor.operacionAnd(vectorUno, vectorDos);
    }
    
    @Test(expected = NullPointerException.class)
    public void test_operacionAnd_cuando_vectorUno_viene_nulo() {
    	Boolean[] vectorUno = null;
    	Boolean[] vectorDos = new Boolean[]{true, true, false, false, true, false, false};
    	
    	monitor.operacionAnd(vectorUno, vectorDos);
    }
    
    @Test(expected = NullPointerException.class)
    public void test_operacionAnd_cuando_vectorDos_viene_nulo() {
    	Boolean[] vectorUno = new Boolean[]{false, false, false, true, false, true};;
    	Boolean[] vectorDos = null;
    	
    	monitor.operacionAnd(vectorUno, vectorDos);
    }
}
