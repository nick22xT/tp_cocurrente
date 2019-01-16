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
    	
    	boolean a[]= {true,true,true,false};
    	boolean b[]= {false,true,true,true};
    	boolean res[];
    	boolean expected[]= {false,true,true,false};
    	
    	res=gestor.and(a, b);
    	
    	assertEquals(expected, res);

    }

    @Test
    public void cuantos_test(){

    }
}
