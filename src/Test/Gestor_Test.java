package Test;

import Monitor.GestorDeMonitor;
import Monitor.Politicas;
import Monitor.RDP;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Gestor_Test {

    RDP rdp = new RDP();
    Politicas politica = new Politicas(6);
    GestorDeMonitor gestor = new GestorDeMonitor(rdp, politica);

    @Test
    public void getVc_test(){

    }

    @Test
    public void and_test(){
    	
    	boolean a[] = {true,true,true,false};
    	boolean b[] = {false,true,true,true};
    	boolean res[] = gestor.and(a, b);
    	
    	assertEquals("El valor de la posicion 0 no es correcto", false , res[0]);
        assertEquals("El valor de la posicion 1 no es correcto", true, res[1]);
        assertEquals("El valor de la posicion 2 no es correcto", true , res[2]);
        assertEquals("El valor de la posicion 3 no es correcto", false, res[3]);

    }

    @Test
    public void cuantos_test(){
    	boolean[] a= {true, false, true};
    	assertEquals(2, gestor.cuantos(a));
    }
}
