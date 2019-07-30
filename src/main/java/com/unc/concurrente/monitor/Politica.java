package com.unc.concurrente.monitor;

import java.util.Random;

import com.unc.concurrente.utils.Prioridades;
import com.unc.concurrente.validations.ValidatePolitics;

public class Politica {
	
	private static final Prioridades[] PRIORIDADES = new Prioridades[]{Prioridades.T6, Prioridades.T8, Prioridades.T0, Prioridades.T3,
			Prioridades.T1, Prioridades.T4, Prioridades.T2,  Prioridades.T5, Prioridades.T10, Prioridades.T16, Prioridades.T17,
			Prioridades.T12, Prioridades.T14, Prioridades.T15, Prioridades.T11, Prioridades.T13, Prioridades.T18, Prioridades.T19,
		    Prioridades.T9, Prioridades.T7};

	private Politica() {
		throw new IllegalStateException();
	}

	public static Integer cual(Boolean[] m, Integer[] integers){
		int queueNumber = 0;
		for(int i = 0; i < m.length; i++) {
			if(m[PRIORIDADES[i].getNumPrioridad()]) {
				queueNumber = PRIORIDADES[i].getNumPrioridad();
				break;
			}
		}
		
		if((queueNumber == 14 || queueNumber == 15) && (m[14] && m[15]))
			queueNumber = controlExit();
		
		try {
			ValidatePolitics.validatePolitics(integers, queueNumber);
		} catch(IllegalStateException e) {
			return -1;
		}
		
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
