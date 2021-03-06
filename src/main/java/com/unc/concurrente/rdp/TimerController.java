package com.unc.concurrente.rdp;

import com.unc.concurrente.model.ParametrosIniciales;
import com.unc.concurrente.utils.InstantsTime;

/**
 * TimeController: esta clase forma parte de la red de petri,
 *  se encarga de llevar el control del tiempo que las transiciones van sensibilizadas asi como
 *  el control de las ventanas de tiempo a la hora de querer disparar una transicion y los calculos de los
 *  tiempos de sleep para los hilos que quieren disparar sus transiciones antes de haber llegado al limite inferior de
 *  su ventana de tiempo.
 *
 */
public class TimerController {
	private boolean[] esperando;
	private long[] tiempoDesdeSensibilizado;
	private Integer[] alfa;
	private Integer[] beta;
	

	public TimerController(ParametrosIniciales params) {
		this.alfa = params.getTiempos().getAlfa();
		this.beta = params.getTiempos().getBeta();
		this.tiempoDesdeSensibilizado = new long[params.getTransiciones()];
		this.esperando = new boolean[params.getTransiciones()];
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
		
		return timeSleep > 0 ? timeSleep : 0L; 
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
