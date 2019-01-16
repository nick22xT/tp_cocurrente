package Test;

import Monitor.GestorDeMonitor;
import Monitor.Politicas;
import Monitor.RDP;

<<<<<<< HEAD
import static org.junit.Assert.assertArrayEquals;
=======
>>>>>>> branch 'master' of https://github.com/nick22xT/tp_cocurrente.git
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
    	boolean[] a= {true, false, true};
    	assertEquals(2, gestor.cuantos(a));
    }
}
