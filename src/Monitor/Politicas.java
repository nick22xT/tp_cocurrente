package Monitor;

import java.util.Random;

import Validations.ValidatePolitics;

public class Politicas {
	
	private static Prioridades[] prioridades = new Prioridades[]{Prioridades.T6, Prioridades.T16, Prioridades.T17, Prioridades.T8, Prioridades.T10, Prioridades.T12, Prioridades.T0,
			Prioridades.T2, Prioridades.T1, Prioridades.T5, Prioridades.T4, Prioridades.T3, Prioridades.T14, Prioridades.T15,
			Prioridades.T9, Prioridades.T11, Prioridades.T13, Prioridades.T7, Prioridades.T18, Prioridades.T19};

	
	private Politicas() {
		throw new IllegalStateException();
	}

	public static int cual(boolean[] m, Cola[] colas, int[] petriSate){
		int queueNumber = 0;
		for(int i = 0; i < m.length; i++) {
			if(m[prioridades[i].getNumPrioridad()]) {
				queueNumber = prioridades[i].getNumPrioridad();
				break;
			}
		}
		
		if((queueNumber == 14 || queueNumber == 15) && (m[14] && m[15])) {
			queueNumber = controlExit();
		}
		
		ValidatePolitics.validatePolitics(petriSate, queueNumber);
		return queueNumber;
	}
	

	static int controlExit() {
		Random random = new Random(System.currentTimeMillis());
		int value = random.nextInt(100);
		if(value <= 49) {
			return 14;
		} else {
			return 15;
		}
	}
}
