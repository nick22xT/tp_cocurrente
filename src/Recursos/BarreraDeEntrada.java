package Recursos;

import java.util.concurrent.TimeUnit;

import Monitor.GestorDeMonitor;

public class BarreraDeEntrada implements Runnable {
	private GestorDeMonitor monitor;
	private int transicion;
	private String descripcion;
	
	public BarreraDeEntrada(GestorDeMonitor monitor, int transicion, String descripcion) {
		super();
		this.monitor = monitor;
		this.transicion = transicion;
		this.descripcion = descripcion;
	}

	@Override
	public void run() {
		while(true){
			monitor.dispararTransicion(transicion);
			System.out.printf(Thread.currentThread().getName() + " - Se abre %s, Se espera para subir al piso asignado.\n", descripcion);
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
