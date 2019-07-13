package com.unc.concurrente.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CommonMethodsTest {
	
	@Test
    public void test_operacionAnd_se_espera_true_en_la_posicion_cero_y_uno() {
    	Boolean[] vector1 = new Boolean[]{true, true, false, true, false, true};
    	Boolean[] vector2 = new Boolean[]{true, true, false, false, true, false};
    	
    	Boolean[] resultado = CommonMethods.operacionAnd(vector1, vector2);
    	
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
    	
    	Boolean[] resultado = CommonMethods.operacionAnd(vector1, vector2);
    	
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
    	
    	CommonMethods.operacionAnd(vectorUno, vectorDos);
    }
    
    @Test(expected = NullPointerException.class)
    public void test_operacionAnd_cuando_vectorUno_viene_nulo() {
    	Boolean[] vectorUno = null;
    	Boolean[] vectorDos = new Boolean[]{true, true, false, false, true, false, false};
    	
    	CommonMethods.operacionAnd(vectorUno, vectorDos);
    }
    
    @Test(expected = NullPointerException.class)
    public void test_operacionAnd_cuando_vectorDos_viene_nulo() {
    	Boolean[] vectorUno = new Boolean[]{false, false, false, true, false, true};;
    	Boolean[] vectorDos = null;
    	
    	CommonMethods.operacionAnd(vectorUno, vectorDos);
    }

}
