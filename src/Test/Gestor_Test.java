package Test;

import Monitor.GestorDeMonitor;
import Monitor.Politicas;
import Monitor.RDP;
import org.junit.Test;

public class Gestor_Test {

    RDP rdp = new RDP();
    Politicas politica = new Politicas(6);
    GestorDeMonitor gestor = new GestorDeMonitor(rdp, politica);

   /* @Test
    public void getVc_est(){

    }

    @Test
    public void and_test(){

    }

    @Test
    public void cuantos_test(){

    }*/
}
