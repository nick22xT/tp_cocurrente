package com.unc.concurrente.utils;

import java.util.Objects;

public class CommonMethods {
	
	private CommonMethods() {
		throw new IllegalStateException("Utility class");
	}
	
	/**
	 * realiza la operancion logica AND coordenada a coordenada entre dos vectores
	 * @param vectorUno
	 * @param vectorDos
	 * @return un vector booleano con los resultados de la operacion AND.
	 */
	public static Boolean[] operacionAnd( Boolean[] vectorUno, Boolean[] vectorDos) {
		if(Objects.isNull(vectorUno) || Objects.isNull(vectorDos)) {
			throw new NullPointerException();
		} else if(vectorUno.length != vectorDos.length) {
			throw new IllegalArgumentException();
		} else {
			Boolean[] vAnd = new Boolean[vectorUno.length];
			
			for(int i = 0; i < vAnd.length; i++) {
				vAnd[i] = vectorUno[i] & vectorDos[i];
			}
			return vAnd;
		}
	}

}
