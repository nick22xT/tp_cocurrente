package Monitor;

import java.util.concurrent.Semaphore;

public class GestorDeMonitor {
	private Semaphore mutex;
	private RDP rdp;
	private Politicas politica;
	private Cola[] colas;
	private boolean k;
	
	public GestorDeMonitor(RDP rdp, Politicas politica) {
		this.rdp = rdp;
		this.politica = politica;
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
				boolean[] vs = rdp.sensibilizadas();
				boolean[] vc = getVc();
				boolean[] m = andOperation(vs, vc);
				
				if(cuantos(m) != 0){
					Cola reactivar = this.politica.cual(m, colas);
					reactivar.release();
					return;
				}else{
					k = false;
				}
			}else{
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
	boolean[] getVc(){
		boolean[] vc = new boolean[colas.length];
		
		for(int i = 0; i < colas.length; i++){
			vc[i] = colas[i].quienesEstan();
		}
		return vc;
	}
	
	/**
	 * realiza la operancion logica AND coordenada a coordenada entre dos vectores
	 * @param a
	 * @param b
	 * @return un vector booleano con los resultados de la operacion AND.
	 */
	public boolean[] andOperation(boolean[] a, boolean[] b){
		if(a.length != rdp.getTransiciones() || b.length != rdp.getTransiciones() || a.length != b.length) {
			throw new IllegalArgumentException();
		}
		boolean[] vAnd = new boolean[rdp.getTransiciones()];
		
		for(int i = 0; i < vAnd.length; i++){
			vAnd[i] = a[i] & b[i];
		}
		return vAnd;
	}
	
	/**
	 * Cuenta la cantidad de coordenadas de un vector que contienen el valor true
	 * @param a
	 * @return la cantidad de coordenadas que contienen el valor true
	 */
	public int cuantos(boolean[] a){
		int contador = 0;
		
		for(int i = 0; i < a.length; i++){
			if(a[i] == true){
				contador++;
			}
		}
		return contador;
	}
}
