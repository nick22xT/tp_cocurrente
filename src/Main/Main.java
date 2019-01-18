package Main;

import Monitor.*;
import Recursos.Consumidor;
import Recursos.Productor;

public class Main {

	public static void main(String[] args) {
		
		RDP red = new RDP();
		Politicas politica = new Politicas();
		
		GestorDeMonitor g = new GestorDeMonitor(red, politica);
		
		Thread t0 = new Thread(new Productor(g));
		Thread t1 = new Thread(new Consumidor(g));
		Thread t2 = new Thread(new Productor(g));
		Thread t3 = new Thread(new Productor(g));
		Thread t4 = new Thread(new Productor(g));
		Thread t5 = new Thread(new Productor(g));		
		t0.start();
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		
	}
	
	
}

