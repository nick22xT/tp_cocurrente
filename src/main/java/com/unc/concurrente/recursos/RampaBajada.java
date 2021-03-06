package com.unc.concurrente.recursos;

import java.util.concurrent.TimeUnit;

import com.unc.concurrente.monitor.GestorDeMonitor;


public class RampaBajada implements Runnable {
	private GestorDeMonitor monitor;
	private int primeraTransicion, segundaTransicion, sleep;
	private String rampa, piso;
	

	public RampaBajada(GestorDeMonitor monitor, int primeraTransicion, 
			int segundaTransicion, int sleep, String rampa, String piso) {
		super();
		this.monitor = monitor;
		this.primeraTransicion = primeraTransicion;
		this.segundaTransicion = segundaTransicion;
		this.sleep = sleep;
		this.rampa = rampa;
		this.piso = piso;
	}

	@Override
	public void run() {
		while(true) {
			this.monitor.dispararTransicion(primeraTransicion);
			System.out.printf(Thread.currentThread().getName() + " - Saliendo de la %s.\n", piso);
			try {
				TimeUnit.MILLISECONDS.sleep(sleep);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.monitor.dispararTransicion(segundaTransicion);
			System.out.printf(Thread.currentThread().getName() + " - Esperando para entrar en zona de cajero por %s.\n", rampa);
			try {
				TimeUnit.MILLISECONDS.sleep(sleep);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
