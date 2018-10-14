package Monitor;

public class Politicas {
	
	
	private int transiciones;
	private PrioTrans[] prioridades = {PrioTrans.T1, PrioTrans.T2, PrioTrans.T5, PrioTrans.T0, PrioTrans.T3, PrioTrans.T4};
	private int[][] m_prioridad = {{0,1,0,0,0,0},//T1
                                   {0,0,1,0,0,0},//T2
                                   {0,0,0,0,0,1},//T5
                                   {1,0,0,0,0,0},//T0
                                   {0,0,0,1,0,0},//T3
                                   {0,0,0,0,1,0}};//T4

	public Politicas(int transiciones) {
		
		this.transiciones = transiciones;
		
	}
	
	public Cola cual(boolean[] m, Cola[] colas){
		
		Cola cola = null;
		
		for(int i = 0; i < m.length; i++) {
			
			if(m[i]) {
				
				cola = colas[i];
				break;
			}
		}
		
		return cola;
		
	}
	
	public int[][] getM_prioridad() {
		
		return m_prioridad;
	}
	
	
	public int[] parseInt(boolean[] m) {
		
		int[] aux = new int[m_prioridad.length];
		
		for(int i = 0; i < m.length; i++) {
			
			aux[i] = m[i] ? 1 : 0;
		}
		
		return aux;
	}
	
	/**
	 * multiplica la matriz m_prioridad por un vector de una dimension y de la misma longitud que la matriz
	 * @param vect
	 * @return un vector de una dimension
	 */
	public int[] productMatriz(int[] vect) {
		
		int[] mult = new int[m_prioridad.length];
		
		for(int i = 0; i < m_prioridad.length; i++) {
			
			for(int j = 0; j < m_prioridad.length; j++) {
				
				mult[i] += m_prioridad[i][j]*vect[j];
			}
		}
		
		return mult;
	}

}
