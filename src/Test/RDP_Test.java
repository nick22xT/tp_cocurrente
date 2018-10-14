package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Monitor.RDP;

public class RDP_Test {
	
	private RDP red = new RDP();
	
	
	@Test
	public void testSuma1(){
		
		int[] a = {1, 1, 2, 0, 0};
		int[] b = {2, 0, 1, 0, 1};
		
		int[] res = red.sumar(a, b);
		
		assertEquals(res[0], 3);
		assertEquals(res[1], 1);
		assertEquals(res[2], 3);
		assertEquals(res[3], 0);
		assertEquals(res[4], 1);
	}
	
	@Test
	public void testsuma2(){
		
		int[] a = {1, 1, 2, 0, 0};
		int[] b = {2, 0, 1, 0};
		
		try{
			
			int[] res = red.sumar(a, b);
		}catch(IllegalArgumentException e){
			
			System.out.println(e);
		}
	}
	
	@Test
	public void testSensibilizadas1(){
		
		boolean[] a = red.sensibilizadas();
		
        assertEquals(a[0], true);
        assertEquals(a[1], false);
        assertEquals(a[2], false);
        assertEquals(a[3], false);
        assertEquals(a[4], false);
        assertEquals(a[5], false);
        

	}
	
	
	
	
	@Test
	public void testDisparar1() {
		
		assertEquals(red.disparar(0), true);
	}
	
	@Test
	public void testDisparar2() {
		
		assertEquals(red.disparar(1), false);
	}

}
