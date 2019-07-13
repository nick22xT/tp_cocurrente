package com.unc.concurrente.monitor;

import java.util.Arrays;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;

import com.unc.concurrente.utils.CommonMethods;

public class GestorDeMonitor {
	private Semaphore mutex;
	private RDP rdp;
	private Cola[] colas;
	private boolean k;
	
	public GestorDeMonitor(RDP rdp) {
		this.rdp = rdp;
		this.mutex = new Semaphore(1, true);
		this.colas = new Cola[rdp.getTransiciones()];
		
		for(int i = 0; i < colas.length; i++){
			colas[i] = new Cola();
		}
	}
	
	/**
	 * Intenta disparar una transicion
	 * @param transicion: la transicion que se intenta disparar
	 */
	public void dispararTransicion(int transicion) {
		try {
			mutex.acquire();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		k = true;
		while(k == true) {
			k = rdp.disparar(transicion); // disparar una transicion
			
			if(k == true) { //el hilo puede ejecutar su tarea
				Boolean[] vectorDeSensibilizadas = rdp.getSensibilizadas();
				Boolean[] vectorDeColas = getVectorDeColas();
				Boolean[] m = CommonMethods.operacionAnd(vectorDeSensibilizadas, vectorDeColas);
				
				if(contarSensibilizadas(m) != 0) {
					colas[Politica.cual(m, rdp.getM_actual())].release();
					return;
				} else {
					k = false;
				}
			} else {
				mutex.release();
				colas[transicion].acquire();//se pone al hilo en la cola de la transicion
			}
		}
		mutex.release();
		return;
	}
	
	/**
	 * Genera un vector de booleanos indicando las colas de las transiciones en las que hay al menos
	 * un hilo esperando para poder disparar dichas transiciones. 
	 * @return un vector de booleanos
	 */
	Boolean[] getVectorDeColas() {
		Boolean[] vc = new Boolean[colas.length];
		
		for(int i = 0; i < colas.length; i++){
			vc[i] = colas[i].quienesEstan();
		}
		return vc;
	}
	
	/**
	 * Cuenta la cantidad de coordenadas de un vector que contienen el valor true
	 * @param a
	 * @return la cantidad de coordenadas que contienen el valor true
	 */
	public int contarSensibilizadas(Boolean[] a) {
		return Arrays.asList(a).stream().filter(d -> d == true).collect(Collectors.toList()).size();
	}
}
