package Recursos;

import Monitor.GestorDeMonitor;

public class Productor implements Runnable {
	
	private GestorDeMonitor g;

	public Productor(GestorDeMonitor g) {
		
		super();
		this.g = g;
	}



	@Override
	public void run() {
		
		int cont = 0;
		
		while(true){
			
			g.dispararTransicion(0);
			System.out.printf(cont + " Productor %s: Poniendo en el buffer.\n", Thread.currentThread().getName());
			g.dispararTransicion(3);
			g.dispararTransicion(4);
			cont++;
		}
	}

}
