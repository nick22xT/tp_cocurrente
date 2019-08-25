package com.unc.concurrente.model;

public class ParametrosIniciales {
	
	Integer plazas;
	Integer transiciones;
	Boolean[] guardas;
	Integer[] marcaInicial;
	Integer[][] incidencia;
	Tiempos tiempos;
	
	
	public Boolean[] getGuardas() {
		return guardas;
	}
	public void setGuardas(Boolean[] guardas) {
		this.guardas = guardas;
	}
	public Integer getPlazas() {
		return plazas;
	}
	public void setPlazas(Integer plazas) {
		this.plazas = plazas;
	}
	public Integer getTransiciones() {
		return transiciones;
	}
	public void setTransiciones(Integer transiciones) {
		this.transiciones = transiciones;
	}
	public Integer[] getM0() {
		return marcaInicial;
	}
	public void setM0(Integer[] m0) {
		marcaInicial = m0;
	}
	public Integer[][] getIncidencia() {
		return incidencia;
	}
	public void setIncidencia(Integer[][] incidencia) {
		this.incidencia = incidencia;
	}
	public Tiempos getTiempos() {
		return tiempos;
	}
	public void setTiempos(Tiempos tiempos) {
		this.tiempos = tiempos;
	}

}
