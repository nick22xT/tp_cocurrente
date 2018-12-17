package Main;

import Monitor.*;
import ProductorComsumidor.Consumidor;
import ProductorComsumidor.Productor;

public class Main {

	public static void main(String[] args) {
		
		RDP red = new RDP();
		Politicas politica = new Politicas(red.getTransiciones());
		
		GestorDeMonitor g = new GestorDeMonitor(red, politica);
		
		Thread t0 = new Thread(new Productor(g));
		Thread t1 = new Thread(new Consumidor(g));
		
		t0.start();
		t1.start();
		
	}
	
	
}

