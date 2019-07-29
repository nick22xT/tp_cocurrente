package com.unc.concurrente.rdp;

import com.unc.concurrente.utils.ShootingStates;
import com.unc.concurrente.validations.ValidateInvariats;

public class RDPTemporal extends RDP {
	private TimerController temporizador;
	
	public RDPTemporal() {
		super();
		this.temporizador = new TimerController(super.getTransiciones());
		this.temporizador.setNuevoTimeStamp(super.getSensibilizadasPorMarca());
		
	}
	
	/**
	 * Intenta disparar la red de Petri.
	 */
	public ShootingStates dispararRed(int transicion) {
		Boolean[] sensibilizadas = super.getSensibilizadasPorMarca();
		
		if(sensibilizadas[transicion]) {
			ShootingStates estadoDeTransicion = null;
			switch(temporizador.testVentanaTiempo(transicion)) {
			case ANTES:
				temporizador.setEsperando(transicion);
				estadoDeTransicion = ShootingStates.SLEEP;
				break;
			case ENTRE:
				super.disparar(transicion);
				ValidateInvariats.ValidateInvariants(super.getM_actual());
				temporizador.setNuevoTimeStamp(super.getSensibilizadasPorMarca());
				temporizador.resetEsperando(transicion);
				estadoDeTransicion = ShootingStates.SUCCESS;
				break;
			case DESPUES:
				estadoDeTransicion = ShootingStates.FAIL;
				break;
			}
			return estadoDeTransicion;
		} else {
			return ShootingStates.FAIL;
		}
	}
	
	public Long getTimeStamp(Integer transicion) {
		return this.temporizador.getTimeSleep(transicion);
	}
	
}
