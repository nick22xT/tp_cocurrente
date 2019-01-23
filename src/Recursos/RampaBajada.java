package Recursos;

import java.util.concurrent.TimeUnit;

import Monitor.GestorDeMonitor;

public class RampaBajada implements Runnable {
	private GestorDeMonitor monitor;
	private int primeraTransicion, segundaTransicion;
	private String rampa, piso;

	public RampaBajada(GestorDeMonitor monitor, int primeraTransicion, 
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
			System.out.printf(Thread.currentThread().getName() + " - Saliendo del %s.\n", piso);
			System.out.printf(Thread.currentThread().getName() + " - Bajando por la %s.\n", rampa);
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.monitor.dispararTransicion(segundaTransicion);
			System.out.println(Thread.currentThread().getName() + " - Entrando a la zona de la Caja para pagar.");
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
