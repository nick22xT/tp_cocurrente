package Recursos;

import java.util.concurrent.TimeUnit;

import Monitor.GestorDeMonitor;

public class ZonaDeCaja implements Runnable{
	private GestorDeMonitor monitor;
	private int transicion;
	private String descripcion;
	
	public ZonaDeCaja(GestorDeMonitor monitor, int transicion, String descripcion) {
		this.monitor = monitor;
		this.transicion = transicion;
		this.descripcion = descripcion;
	}

	@Override
	public void run() {
		while(true) {
			this.monitor.dispararTransicion(transicion);
			System.out.printf(Thread.currentThread().getName() + " - Ingresando a zona de cajero hacia la %s.\n", descripcion);
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	

}
