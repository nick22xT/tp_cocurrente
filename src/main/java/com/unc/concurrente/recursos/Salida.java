package com.unc.concurrente.recursos;

import java.util.concurrent.TimeUnit;

import com.unc.concurrente.monitor.GestorDeMonitor;


public class Salida implements Runnable {
	private GestorDeMonitor monitor;
	private int transicionUno, transicionDos, sleep;
	private String descripcion;
	
	public Salida(GestorDeMonitor monitor, int transicionUno, int transicionDos, int sleep, String descripcion) {
		super();
		this.monitor = monitor;
		this.transicionUno = transicionUno;
		this.transicionDos = transicionDos;
		this.sleep = sleep;
		this.descripcion = descripcion;
	}

	@Override
	public void run() {
		while(true) {
			this.monitor.dispararTransicion(transicionUno);
			System.out.printf(Thread.currentThread().getName() + " - Dirigiendose a la %s.\n", descripcion);
			try {
				TimeUnit.MILLISECONDS.sleep(sleep);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.monitor.dispararTransicion(transicionDos);
			System.out.printf(Thread.currentThread().getName() + " - Saliendo del estacionamiento por la %s.\n", descripcion);
			try {
				TimeUnit.MILLISECONDS.sleep(sleep);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
