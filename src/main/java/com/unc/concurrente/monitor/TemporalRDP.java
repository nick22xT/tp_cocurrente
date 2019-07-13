package com.unc.concurrente.monitor;

import com.unc.concurrente.utils.CommonMethods;

public class TemporalRDP extends RDP {
	
	
	/**
	 * Genera un vector con todas las transiciones sensibilizadas de la red de petri basandose en
	 * el estado actual de la red de petri y en las marcas de tiempo de cada transicion.
	 * @return Boolean[]
	 */
	public Boolean[] getSensibilizadas() {
		return CommonMethods.operacionAnd(super.getSensibilizadas(),
				getSensibilizadasPorTiempo());
	}
	
	/**
	 * Genera un vector con las transiciones que estan sensibilizadas porque estan dentro de su
	 * ventana de tiempo.
	 * @return
	 */
	Boolean[] getSensibilizadasPorTiempo() {
		return null;
	}

}
