package Recursos;

import java.util.concurrent.TimeUnit;

import Monitor.GestorDeMonitor;

public class RampaSubida implements Runnable {
	private GestorDeMonitor monitor;
	private int primeraTransicion, segundaTransicion;
	private String rampa, piso;

	public RampaSubida(GestorDeMonitor monitor, int primeraTransicion, 
			int segundaTransicion, String rampa, String piso) {
		super();
		this.monitor = monitor;
		this.primeraTransicion = primeraTransicion;
		this.segundaTransicion = segundaTransicion;
		this.rampa = rampa;
		this.piso = piso;
	}

	@Override
	public void run() {
		while(true) {
			this.monitor.dispararTransicion(primeraTransicion);
			System.out.printf(Thread.currentThread().getName() + " - Subiendo por la %s.\n", rampa);
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.monitor.dispararTransicion(segundaTransicion);
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
