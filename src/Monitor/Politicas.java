package Monitor;

public class Politicas {
	@SuppressWarnings("unchecked")
	
	private int[][] m_prioridad = new int[][] 	{{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
												{0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
												{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
												{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
												{0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
												{0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},
												{0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
												{0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0},
												{0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},
												{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0},
												{0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
												{0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
												{0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
												{0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
												{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
												{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
												{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
												{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1}};

	
	public Politicas() {
		super();
	}

	public Cola cual(boolean[] m, Cola[] colas){
		
		Cola cola = null;
		int[] m_int=parseInt(m);
		int[] res_prod=productMatriz(m_int);
		
		/*for(int i = 0; i < m.length; i++) {
			
			if(m[i]) {
				
				cola = colas[i];
				break;
			}
		}*/
		
		for(int i=0; i<res_prod.length; i++)
		{
			if(res_prod[i]==1)
			{
				cola=colas[i];
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
