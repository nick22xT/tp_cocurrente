package com.unc.concurrente.rdp;

import com.unc.concurrente.utils.InstantsTime;

/**
 * TimeController: esta clase forma parte de la red de petri,
 *  se encarga de llevar el control del tiempo que las transiciones van sensibilizadas asi como
 *  el control de las ventanas de tiempo a la hora de querer disparar una transicion y los calculos de los
 *  tiempos de sleep para los hilos que quieren disparar sus transiciones antes de haber llegado al limite inferior de
 *  su ventana de tiempo.
 * @author Nico
 *
 */
public class TimerController {
	private static final int INFINITO = 1000000000;
	private Integer[] alfa = new Integer[]{2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 0, 0};
	private Integer[] beta = new Integer[]{INFINITO, INFINITO, INFINITO, INFINITO, INFINITO, INFINITO, INFINITO,
			                               INFINITO, INFINITO, INFINITO, INFINITO, INFINITO, INFINITO, INFINITO,
			                               INFINITO, INFINITO, INFINITO, INFINITO, INFINITO, INFINITO};
	private boolean[] esperando;
	private long[] tiempoDesdeSensibilizado;
	private int totalTransiciones;
	

	public TimerController(int totalTansiciones) {
		this.totalTransiciones = totalTansiciones;
		this.tiempoDesdeSensibilizado = new long[totalTansiciones];
		this.esperando = new boolean[totalTansiciones];
	}
	
	/**
	 * Comprueba que transiciones se encuentran dentro de su ventana de tiempo.
	 *
	 * @return	Boolean[]
	 */
	public Boolean[] testVentanaTiempo() {
		Boolean[] dentroDelIntervalo = new Boolean[totalTransiciones];
		Long currentTime = System.currentTimeMillis()/1000;
		Long tiempo;
		
		for(int i = 0; i < dentroDelIntervalo.length; i++) {
			tiempo = currentTime - tiempoDesdeSensibilizado[i];
			
			if (tiempo < alfa[i] || tiempo > beta[i]) { 
				dentroDelIntervalo[i] = false; 
			} else { 
				dentroDelIntervalo[i] = true; 
			}
		}
		return dentroDelIntervalo;
	}
	
	/**
	 * Comprueba si la transicion se encuentra dentro de su ventana de tiempo.
	 *
	 * @return	InstantsTime
	 */
	public InstantsTime testVentanaTiempo(Integer transicion) {
		Long tiempo = System.currentTimeMillis()/1000 - tiempoDesdeSensibilizado[transicion];
			
			if (tiempo < alfa[transicion]) { 
				return InstantsTime.ANTES; 
			} else if(tiempo > beta[transicion]) {
				return InstantsTime.DESPUES;
			} else { 
				return InstantsTime.ENTRE; 
			}
	}
	
	/**
	 * Obtiene el tiempo que tiene que dormir un Thread, en segundos, antes de llegar al alfa de la transicion.
	 *
	 * @param  	transicion	Transicion a averiguar su tiempo
	 * @return	Tiempo que tiene que dormir el Thread hasta que se llegue al alfa de la transicion
	 */
	public Long getTimeSleep(int transicion) {
		Long timeSleep = (long) alfa[transicion].intValue() + 
				tiempoDesdeSensibilizado[transicion] - (System.currentTimeMillis()/1000);
		
		if (timeSleep > 0)
			return timeSleep; 
		else
			return 0L; 
	}
	
	/**
	 * Sobreescribe el timestamp de sensibilizacion de las transiciones en la red.
	 * Dado un conjunto de transiciones, sobreescribe su momento de sensibilizacion
	 * por el instante actual, solo si no hay ningun hilo esperando.
	 * Si no esta sensibilizada su tiempo es cero.
	 *
	 * @param  sensibilizadas	Conjunto de transiciones recientemente sensibilizadas
	 */
	public void setNuevoTimeStamp(Boolean[] sensibilizadas) {
		for (int i = 0; i < sensibilizadas.length; i++) {
			if (sensibilizadas[i].booleanValue() && esperando[i] == false) {
				tiempoDesdeSensibilizado[i] = System.currentTimeMillis()/1000;
			}
		}
	}
	
	public boolean[] getEsperando() {
		return this.esperando;
	}
	
	public void setEsperando(int transicion) {
		esperando[transicion] = true;
	}
	
	public void resetEsperando(int transicion) {
		esperando[transicion] = false;
	}
}
