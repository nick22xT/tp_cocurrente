package com.unc.concurrente.mocks;

import com.unc.concurrente.monitor.GestorDeMonitor;

public class RecursoMock implements Runnable {
	
	private int transicion;
	private GestorDeMonitor monitor;
	
	public RecursoMock(int transicion, GestorDeMonitor monitor) {
		this.transicion = transicion;
		this.monitor = monitor;
	}

	@Override
	public void run() {
		monitor.dispararTransicion(transicion);
	}

}
