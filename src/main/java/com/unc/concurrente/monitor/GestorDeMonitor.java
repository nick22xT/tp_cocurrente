package com.unc.concurrente.monitor;

import java.util.Arrays;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;

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
	public void dispararTransicion(int transicion){
		try{
			mutex.acquire();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		k = true;
		while(k == true){
			k = rdp.disparar(transicion); // disparar una transicion
			
			if(k == true){ //el hilo puede ejecutar su tarea
				Boolean[] vs = rdp.sensibilizadas();
				Boolean[] vc = getVc();
				Boolean[] m = andOperation(vs, vc);
				
				if(cuantos(m) != 0) {
					int queueNumber = Politicas.cual(m, colas, rdp.getM_actual());
					colas[queueNumber].release();
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
	Boolean[] getVc(){
		Boolean[] vc = new Boolean[colas.length];
		
		for(int i = 0; i < colas.length; i++){
			vc[i] = colas[i].quienesEstan();
		}
		return vc;
	}
	
	/**
	 * realiza la operancion logica AND coordenada a coordenada entre dos vectores
	 * @param vs
	 * @param vc
	 * @return un vector booleano con los resultados de la operacion AND.
	 */
	public Boolean[] andOperation(Boolean[] vs, Boolean[] vc){
		if(vs.length != rdp.getTransiciones() || vc.length != rdp.getTransiciones() || vs.length != vc.length) {
			throw new IllegalArgumentException();
		}
		Boolean[] vAnd = new Boolean[rdp.getTransiciones()];
		
		for(int i = 0; i < vAnd.length; i++){
			vAnd[i] = vs[i] & vc[i];
		}
		return vAnd;
	}
	
	/**
	 * Cuenta la cantidad de coordenadas de un vector que contienen el valor true
	 * @param a
	 * @return la cantidad de coordenadas que contienen el valor true
	 */
	public int cuantos(Boolean[] a){
		return Arrays.asList(a).stream().filter(d -> d == true).collect(Collectors.toList()).size();
	}
}