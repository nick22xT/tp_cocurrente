package Test;

import static org.junit.Assert.*;
import org.junit.Test;

import Monitor.RDP;

public class RDPTest {
	
	private RDP red = new RDP();
	
	
	@Test
	public void suma_Method_Test(){
		int[] a = {1, 1, 2, 0, 0};
		int[] b = {2, 0, 1, 0, 1};
		int[] res = red.sumar(a, b);
		
		assertEquals("El valor en la posicion 0 no es el eserado", 3, res[0]);
		assertEquals("El valor en la posicion 1 no es el eserado", 1, res[1]);
		assertEquals("El valor en la posicion 2 no es el eserado", 3, res[2]);
		assertEquals("El valor en la posicion 3 no es el eserado", 0, res[3]);
		assertEquals("El valor en la posicion 4 no es el eserado", 1, res[4]);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void suma_Method_Test_Illegal_Argument(){
		int[] a = {1, 1, 2, 0, 0};
		int[] b = {2, 0, 1, 0};
		red.sumar(a, b);
	}
	
	@Test
	public void sensibilizadas_Method_Test(){
		
		boolean[] a = red.sensibilizadas();
		
        assertEquals("El valor en la posicion 0 no es el eserado", true, a[0]);
        assertEquals("El valor en la posicion 1 no es el eserado", false, a[1]);
        assertEquals("El valor en la posicion 2 no es el eserado", false, a[2]);
        assertEquals("El valor en la posicion 3 no es el eserado", false, a[3]);
        assertEquals("El valor en la posicion 4 no es el eserado", false, a[4]);
        assertEquals("El valor en la posicion 5 no es el eserado", false, a[5]);
        

	}
	
	@Test
	public void disparar_Method_T0_Test() {
		
		assertEquals("El valor retornado no fue el esperado", true, red.disparar(0));
	}
	
	@Test
	public void disparar_Method_T1_Test() {
		
		assertEquals("El valor retornado no fue el esperado", false, red.disparar(1));
	}
}
