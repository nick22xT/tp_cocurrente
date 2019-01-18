package Test;

import Monitor.GestorDeMonitor;
import Monitor.Politicas;
import Monitor.RDP;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GestorDeMonitorTest {

    RDP rdp = new RDP();
    Politicas politica = new Politicas();
    GestorDeMonitor gestor = new GestorDeMonitor(rdp, politica);

    @Test
    public void getVc_test(){

    }

    @Test(expected = IllegalArgumentException.class)
    public void and_test_Illegal_Argument(){
    	
    	boolean a[] = {true,true,true,false};
    	boolean b[] = {false,true,true,true};
    	gestor.andOperation(a, b);
    }

    @Test
    public void cuantos_test(){
    	boolean[] a= {true, false, true};
    	assertEquals(2, gestor.cuantos(a));
    }
}
