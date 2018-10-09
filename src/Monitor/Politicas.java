package Monitor;

public class Politicas {
	
	int[][] m_prioridad;
	private int transiciones;
	
	public Politicas(int transiciones) {
		
		this.transiciones = transiciones;
		this.m_prioridad = new int[transiciones][transiciones];
		
	}
	
	public Cola cual(boolean[] m, Cola[] colas){
		
		if(m[1]) {
			
			return colas[1];
		}
		return colas[0];
		
	}

}
