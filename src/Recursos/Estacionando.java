package Recursos;

import java.util.concurrent.TimeUnit;

import Monitor.GestorDeMonitor;

public class Estacionando implements Runnable {
	private GestorDeMonitor monitor;
	private int transicion;
	private String piso;
	
	

	public Estacionando(GestorDeMonitor monitor, int transicion, String piso) {
		super();
		this.monitor = monitor;
		this.transicion = transicion;
		this.piso = piso;
	}



	@Override
	public void run() {
		while(true) {
			this.monitor.dispararTransicion(transicion);
			System.out.printf(Thread.currentThread().getName() + " - Estacionando en el %s.\n", piso);
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
