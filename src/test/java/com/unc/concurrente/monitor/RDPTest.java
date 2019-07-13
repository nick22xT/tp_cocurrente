package com.unc.concurrente.monitor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.unc.concurrente.mocks.RDPMock;
import com.unc.concurrente.monitor.RDP;


public class RDPTest {
	
	private RDP red;
	
	@Before
	public void setUp() { 
		red = RDPMock.getRDP();
	}
	
	@Test
	public void test_obtener_sensibilizadas_sin_haber_disparado_la_red() {
		Boolean[] sensibilizadas = red.getSensibilizadas();
		
        assertEquals("El valor en la posicion 0 no es el eserado", true, sensibilizadas[0]);
        assertEquals("El valor en la posicion 1 no es el eserado", false, sensibilizadas[1]);
        assertEquals("El valor en la posicion 2 no es el eserado", false, sensibilizadas[2]);
        assertEquals("El valor en la posicion 3 no es el eserado", false, sensibilizadas[3]);
        assertEquals("El valor en la posicion 4 no es el eserado", false, sensibilizadas[4]);
        assertEquals("El valor en la posicion 5 no es el eserado", false, sensibilizadas[5]);
	}
	
	@Test
	public void test_obtener_sensibilizadas_despues_de_disparar_transicion_cero() {
		boolean resultadoDisparo = red.disparar(0);
		Boolean[] sensibilizadas = red.getSensibilizadas();
		
		assertEquals("El valor retornado no fue el esperado", true, resultadoDisparo);
		
		assertEquals("El valor en la posicion 0 no es el eserado", false, sensibilizadas[0]);
        assertEquals("El valor en la posicion 1 no es el eserado", false, sensibilizadas[1]);
        assertEquals("El valor en la posicion 2 no es el eserado", false, sensibilizadas[2]);
        assertEquals("El valor en la posicion 3 no es el eserado", true, sensibilizadas[3]);
        assertEquals("El valor en la posicion 4 no es el eserado", false, sensibilizadas[4]);
        assertEquals("El valor en la posicion 5 no es el eserado", false, sensibilizadas[5]);
	}
	
	@Test
	public void test_obtener_sensibilizadas_despues_de_disparar_la_transicion_cero_y_luego_la_transicion_tres() {
		boolean[] resultadosDeDisparo = new boolean[2];
		resultadosDeDisparo[0] = red.disparar(0);
		resultadosDeDisparo[1] = red.disparar(3);
		Boolean[] sensibilizadas = red.getSensibilizadas();
		
		assertEquals("El valor retornado no fue el esperado", true, resultadosDeDisparo[0]);
		assertEquals("El valor retornado no fue el esperado", true, resultadosDeDisparo[1]);
		
		assertEquals("El valor en la posicion 0 no es el eserado", false, sensibilizadas[0]);
        assertEquals("El valor en la posicion 1 no es el eserado", true, sensibilizadas[1]);
        assertEquals("El valor en la posicion 2 no es el eserado", false, sensibilizadas[2]);
        assertEquals("El valor en la posicion 3 no es el eserado", false, sensibilizadas[3]);
        assertEquals("El valor en la posicion 4 no es el eserado", true, sensibilizadas[4]);
        assertEquals("El valor en la posicion 5 no es el eserado", false, sensibilizadas[5]);
	}
	
	@Test
	public void test_disparar_transicion_cero() {
		boolean resultadoDisparo = red.disparar(0);
		Integer[] marcado = red.getM_actual();
		
		assertEquals("El valor retornado no fue el esperado", true, resultadoDisparo);
		
		assertEquals("El valor en la posicion 0 no es el eserado", 0, marcado[0].intValue());
        assertEquals("El valor en la posicion 1 no es el eserado", 1, marcado[1].intValue());
        assertEquals("El valor en la posicion 2 no es el eserado", 1, marcado[2].intValue());
        assertEquals("El valor en la posicion 3 no es el eserado", 0, marcado[3].intValue());
        assertEquals("El valor en la posicion 4 no es el eserado", 0, marcado[4].intValue());
        assertEquals("El valor en la posicion 5 no es el eserado", 0, marcado[5].intValue());
        assertEquals("El valor en la posicion 6 no es el eserado", 0, marcado[6].intValue());
        assertEquals("El valor en la posicion 7 no es el eserado", 0, marcado[7].intValue());
        assertEquals("El valor en la posicion 8 no es el eserado", 4, marcado[8].intValue());

	}
	
	@Test
	public void test_disparar_transicion_uno() {
		boolean resultadoDisparo = red.disparar(1);
		Integer[] marcado = red.getM_actual();
		
		assertEquals("El valor retornado no fue el esperado", false, resultadoDisparo);
		
		assertEquals("El valor en la posicion 0 no es el eserado", 1, marcado[0].intValue());
        assertEquals("El valor en la posicion 1 no es el eserado", 1, marcado[1].intValue());
        assertEquals("El valor en la posicion 2 no es el eserado", 0, marcado[2].intValue());
        assertEquals("El valor en la posicion 3 no es el eserado", 0, marcado[3].intValue());
        assertEquals("El valor en la posicion 4 no es el eserado", 0, marcado[4].intValue());
        assertEquals("El valor en la posicion 5 no es el eserado", 0, marcado[5].intValue());
        assertEquals("El valor en la posicion 6 no es el eserado", 1, marcado[6].intValue());
        assertEquals("El valor en la posicion 7 no es el eserado", 0, marcado[7].intValue());
        assertEquals("El valor en la posicion 8 no es el eserado", 5, marcado[8].intValue());
	}
	
	@Test
	public void test_disparar_transicion_cero_y_luego_la_transicion_tres() {
		boolean[] resultadosDeDisparo = new boolean[2];
		resultadosDeDisparo[0] = red.disparar(0);
		resultadosDeDisparo[1] = red.disparar(3);
		Integer[] marcado = red.getM_actual();
		
		assertEquals("El valor retornado no fue el esperado", true, resultadosDeDisparo[0]);
		assertEquals("El valor retornado no fue el esperado", true, resultadosDeDisparo[1]);
		
		assertEquals("El valor en la posicion 0 no es el eserado", 0, marcado[0].intValue());
        assertEquals("El valor en la posicion 1 no es el eserado", 1, marcado[1].intValue());
        assertEquals("El valor en la posicion 2 no es el eserado", 0, marcado[2].intValue());
        assertEquals("El valor en la posicion 3 no es el eserado", 0, marcado[3].intValue());
        assertEquals("El valor en la posicion 4 no es el eserado", 1, marcado[4].intValue());
        assertEquals("El valor en la posicion 5 no es el eserado", 0, marcado[5].intValue());
        assertEquals("El valor en la posicion 6 no es el eserado", 1, marcado[6].intValue());
        assertEquals("El valor en la posicion 7 no es el eserado", 1, marcado[7].intValue());
        assertEquals("El valor en la posicion 8 no es el eserado", 4, marcado[8].intValue());
	}
	
	
	
	@Test
	public void test_sumar_dos_vectores_de_igual_dimension() {
		Integer[] a = {1, 1, 2, 0, 0};
		Integer[] b = {2, 0, 1, 0, 1};
		Integer[] res = red.sumar(a, b);
		
		assertEquals("El valor en la posicion 0 no es el eserado", 3, res[0].intValue());
		assertEquals("El valor en la posicion 1 no es el eserado", 1, res[1].intValue());
		assertEquals("El valor en la posicion 2 no es el eserado", 3, res[2].intValue());
		assertEquals("El valor en la posicion 3 no es el eserado", 0, res[3].intValue());
		assertEquals("El valor en la posicion 4 no es el eserado", 1, res[4].intValue());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_sumar_dos_vectores_de_distinta_dimension() {
		Integer[] a = {1, 1, 2, 0, 0};
		Integer[] b = {2, 0, 1, 0};
		red.sumar(a, b);
	}
}
