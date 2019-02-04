package Recursos;

import java.util.concurrent.TimeUnit;

import Monitor.GestorDeMonitor;

public class RampaSubida implements Runnable {
	private GestorDeMonitor monitor;
	private int transicion;
	private String rampa, piso;

	public RampaSubida(GestorDeMonitor monitor, int transicion, String rampa) {
		super();
		this.monitor = monitor;
		this.transicion = transicion;
		this.rampa = rampa;
	}

	@Override
	public void run() {
		while(true) {
			this.monitor.dispararTransicion(transicion);
			System.out.printf(Thread.currentThread().getName() + " - Subiendo por la %s.\n", rampa);
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
