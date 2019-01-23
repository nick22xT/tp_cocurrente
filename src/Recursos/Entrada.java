package Recursos;

import java.util.concurrent.TimeUnit;

import Monitor.GestorDeMonitor;

public class Entrada implements Runnable {
	private GestorDeMonitor monitor;
	private int transicion;
	private String descripcion;

	public Entrada(GestorDeMonitor monitor, int transicion, String descripcion) {
		super();
		this.monitor = monitor;
		this.transicion = transicion;
		this.descripcion = descripcion;
	}

	@Override
	public void run() {
		while(true){
			monitor.dispararTransicion(transicion);
			System.out.printf(Thread.currentThread().getName() + " - Ingresa Auto por: %s.\n", descripcion);
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
