package com.unc.concurrente.recursos;

import java.util.concurrent.TimeUnit;

import com.unc.concurrente.monitor.GestorDeMonitor;


public class Entrada implements Runnable {
	private GestorDeMonitor monitor;
	private int transicionUno, transicionDos, sleep;
	private String descripcion;

	public Entrada(GestorDeMonitor monitor, int transicionUno, int transicionDos, int sleep, String descripcion) {
		super();
		this.monitor = monitor;
		this.transicionUno = transicionUno;
		this.transicionDos = transicionDos;
		this.sleep = sleep;
		this.descripcion = descripcion;
	}

	@Override
	public void run() {
		while(true){
			monitor.dispararTransicion(transicionUno);
			System.out.printf(Thread.currentThread().getName() + " - Ingresa Auto por: %s.\n", descripcion);
			try {
				TimeUnit.MILLISECONDS.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			monitor.dispararTransicion(transicionDos);
			System.out.printf(Thread.currentThread().getName() + " - Se abre %s, Se espera para subir al piso asignado.\n", descripcion);
			try {
				TimeUnit.MILLISECONDS.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
