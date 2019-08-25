package com.unc.concurrente.recursos;

import java.util.concurrent.TimeUnit;

import com.unc.concurrente.monitor.GestorDeMonitor;

public class ZonaDeCaja implements Runnable{
	private GestorDeMonitor monitor;
	private int transicion, sleep;
	private String descripcion;
	
	public ZonaDeCaja(GestorDeMonitor monitor, int transicion, int sleep, String descripcion) {
		this.monitor = monitor;
		this.transicion = transicion;
		this.sleep = sleep;
		this.descripcion = descripcion;
	}

	@Override
	public void run() {
		while(true) {
			this.monitor.dispararTransicion(transicion);
			System.out.printf(Thread.currentThread().getName() + " - Ingresando a zona de cajero por la %s.\n", descripcion);
			try {
				TimeUnit.MILLISECONDS.sleep(sleep);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	

}
