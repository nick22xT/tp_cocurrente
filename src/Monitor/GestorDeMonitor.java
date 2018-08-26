package Monitor;

import java.util.concurrent.Semaphore;

public class GestorDeMonitor {
	
	private Semaphore mutex;
	private RDP rdp;
	private Politicas politica;
	private Cola[] colas;
	

	public GestorDeMonitor() {
		
		this.rdp = new RDP();
		this.politica = new Politicas();
		this.mutex = new Semaphore(1, true);
		this.colas = new Cola[rdp.getTransiciones()];
	}
	
	public void dispararTransicion(int transicion){
		
		try{
			mutex.acquire();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		boolean k = true;
		
		while(k == true){
			
			k = rdp.disparar(transicion); // disparar una transicion
			
			if(k == true){ //el hilo puede ejecutar su tarea
				
				int[] vSensibilizadas = rdp.sensibilizadas();
				//Cola[] vColas = un vector con los hilos que esperan el las colas de las transiciones
				
				int m = 0; //vSensibilizadas and vColas
				
				if(m != 0){
					
					this.politica.cual();
					
					mutex.release();
				}else{
					
					mutex.release();
				}
				
			}else{
				
				mutex.release();
				k = false;
			}
		}
	}

}
