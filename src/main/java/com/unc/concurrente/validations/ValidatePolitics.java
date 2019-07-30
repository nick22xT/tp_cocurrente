package com.unc.concurrente.validations;

public class ValidatePolitics {
	
	public static void validatePolitics(Integer[] petriNetState, int queueNumber) {
		if(petriNetState[10] != 0 && queueNumber == 7) { // si hay lugares libres en el piso de arriba y un auto quiere entrar al piso de abajo
			throw new IllegalStateException();
		}
	}
}
