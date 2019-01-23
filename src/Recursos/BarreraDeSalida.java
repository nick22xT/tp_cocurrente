package Recursos;

import java.util.concurrent.TimeUnit;

import Monitor.GestorDeMonitor;

public class BarreraDeSalida implements Runnable {
	private GestorDeMonitor monitor;
	private int transicion;
	private String descripcion;
	
	public BarreraDeSalida(GestorDeMonitor monitor, int transicion, String descripcion) {
		super();
		this.monitor = monitor;
		this.transicion = transicion;
		this.descripcion = descripcion;
	}
	
	@Override
	public void run() {
		while(true) {
			this.monitor.dispararTransicion(transicion);
			System.out.printf(Thread.currentThread().getName() + " - Dirigiendose a la %s.\n", descripcion);
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
