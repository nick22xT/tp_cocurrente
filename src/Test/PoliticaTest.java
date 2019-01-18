package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Monitor.Politicas;

public class PoliticaTest {
	
	Politicas politica = new Politicas();
	
	@Test
	public void parseIntTest_1() {
		
		boolean[] myBoolean = {true,true,true};
		
		int[] test = politica.parseInt(myBoolean);
		
		assertEquals( test[0],1);
		assertEquals( test[1],1);
		assertEquals( test[2],1);
	}
	
	@Test
	public void parseIntTest_2() {
		
		boolean[] myBoolean = {false,false,false};
		
		int[] test = politica.parseInt(myBoolean);
		
		assertEquals( test[0],0);
		assertEquals( test[1],0);
		assertEquals( test[2],0);
	}
	
	@Test
	public void productMatrizTest_1() {
		
		int[] vect = {0,1,1,1,1,1};
		
		int[] resultado = politica.productMatriz(vect);
		
		for(int i = 0; i < resultado.length; i++) {
			
			System.out.println(resultado[i]);
		}
	}

}
