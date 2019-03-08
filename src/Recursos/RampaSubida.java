package Recursos;

import java.util.concurrent.TimeUnit;

import Monitor.GestorDeMonitor;

public class RampaSubida implements Runnable {
	private GestorDeMonitor monitor;
	private int transicion1, transicion2;
	private String rampa, piso;

	public RampaSubida(GestorDeMonitor monitor, int transicion1, int transicion2, String rampa, String piso) {
		super();
		this.monitor = monitor;
		this.transicion1 = transicion1;
		this.transicion2 = transicion2;
		this.rampa = rampa;
		this.piso = piso;
	}

	@Override
	public void run() {
		while(true) {
			this.monitor.dispararTransicion(transicion1);
			System.out.printf(Thread.currentThread().getName() + " - Subiendo por la %s.\n", rampa);
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.monitor.dispararTransicion(transicion2);
			System.out.printf(Thread.currentThread().getName() + " - Entrando al %s.\n", piso);
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
