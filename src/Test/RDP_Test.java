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
	public void testSensibilizadas(){
		
		boolean[] a = red.sensibilizadas();
		
        for(int i = 0; i < a.length; i++){
			
			System.out.println(a[i] + "\n");
		}
        
        System.out.println("###############################################");
        red.disparar(0);
        
        a = red.sensibilizadas();
		
        for(int i = 0; i < a.length; i++){
			
			System.out.println(a[i] + "\n");
		}
        
        System.out.println("###############################################");
        red.disparar(3);
        
        a = red.sensibilizadas();
		
        for(int i = 0; i < a.length; i++){
			
			System.out.println(a[i] + "\n");
		}
        
        System.out.println("###############################################");
        red.disparar(1);
        
        a = red.sensibilizadas();
		
        for(int i = 0; i < a.length; i++){
			
			System.out.println(a[i] + "\n");
		}

	}
	
	
	
	
	@Test
	public void test() {
		
		System.out.print(red.disparar(0) + "\n\n");
		
		for(int i = 0; i < red.getPlazas(); i++){
			
			//System.out.println(red.m_actual[i]);
		}
		
		/*System.out.print(red.disparar(3) + "\n\n");
		
        for(int i = 0; i < red.getPlazas(); i++){
			
			System.out.println(red.m_actual[i]);
		}
		
		/*System.out.print(red.disparar(3) + "\n\n");
		
		System.out.print(red.disparar(1) + "\n\n");
		
		System.out.print(red.disparar(1) + "\n\n");
		
		System.out.print(red.disparar(1) + "\n\n");*/
	}

}
