package ProductorComsumidor;

import Monitor.GestorDeMonitor;

public class Consumidor implements Runnable {
	
	private GestorDeMonitor g;
	

	public Consumidor(GestorDeMonitor g) {
		
		super();
		this.g = g;
	}

	@Override
	public void run() {
		
		int cont = 0;
		
		while(true){
			g.dispararTransicion(1);
			System.out.printf(cont + " Consumidor %s: Sacando del buffer.\n", Thread.currentThread().getName());
			g.dispararTransicion(2);
			g.dispararTransicion(5);
			cont++;
		}

	}

}
