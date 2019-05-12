package com.unc.concurrente.monitor;

public class Cola {
	private boolean notEmpty;
	private int contador; //lleva la cuenta de la cantidad de hilos suspendidos en la cola
	
	public Cola(){
		this.notEmpty = false;
		this.contador = 0;
	}
	
	/**
	 * Permite ver si la cola esta vacia o contiene algun elemento en su interior.
	 * @return true si hay un elemento en su interior, de lo contrario devuelve false.
	 */
	public boolean quienesEstan(){
		return notEmpty;
	}
	
	public synchronized void acquire(){
		this.notEmpty = true;
		this.contador++;
		try{
			wait();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public synchronized void release(){
		if(notEmpty){
			this.contador--;
			if(contador == 0){
				this.notEmpty = false;
				notify();
				return;
			}else{
				notify();
				return;
			}
		}
	}
		
		
		

}
