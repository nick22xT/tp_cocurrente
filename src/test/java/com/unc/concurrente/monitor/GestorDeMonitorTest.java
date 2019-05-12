package com.unc.concurrente.monitor;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GestorDeMonitorTest {

    RDP rdp = new RDP();
    GestorDeMonitor gestor = new GestorDeMonitor(rdp);

    @Test
    public void getVc_test(){

    }

    @Test(expected = IllegalArgumentException.class)
    public void and_test_Illegal_Argument(){
    	
    	Boolean a[] = {true,true,true,false};
    	Boolean b[] = {false,true,true,true};
    	gestor.andOperation(a, b);
    }

    @Test
    public void cuantos_test(){
    	Boolean[] a= {true, false, true, true};
    	assertEquals(3, gestor.cuantos(a));
    }
}
