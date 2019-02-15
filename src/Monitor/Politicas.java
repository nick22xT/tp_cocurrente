package Monitor;

public class Politicas {
	@SuppressWarnings("unchecked")
	
	/**private int[][] m_prioridad = new int[][] { {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}, //T0
												{0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
												{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}, // T2
												{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
												{0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0}, // T4
												{0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},
												{0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0}, // T6
												{0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0},
												{0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0}, // T8
												{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0},
												{0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0}, // T10
												{0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
												{0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0}, // T12
												{0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
												{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0}, // T14
												{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
												{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0}, // T16
												{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1}};**/
	private static Prioridades[] prioridades = new Prioridades[]{Prioridades.T6, Prioridades.T8, Prioridades.T10, Prioridades.T12, Prioridades.T0,
			Prioridades.T1, Prioridades.T2, Prioridades.T3, Prioridades.T4, Prioridades.T5, Prioridades.T14, Prioridades.T15, Prioridades.T16,
			Prioridades.T17, Prioridades.T7, Prioridades.T9, Prioridades.T11, Prioridades.T13, Prioridades.T6};

	
	private Politicas() {
		throw new IllegalStateException();
	}

	public static Cola cual(boolean[] m, Cola[] colas){
		Cola cola = null;
		for(int i = 0; i < m.length; i++) {
			if(m[prioridades[i].getNumPrioridad()]) {
				cola = colas[prioridades[i].getNumPrioridad()];
				break;
			}
		}
		return cola;
	}
}
