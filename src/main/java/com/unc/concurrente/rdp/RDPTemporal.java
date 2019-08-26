package com.unc.concurrente.rdp;

import com.unc.concurrente.model.ParametrosIniciales;
import com.unc.concurrente.utils.ShootingStates;

public class RDPTemporal extends RDP {
	private TimerController temporizador;
	
	public RDPTemporal(ParametrosIniciales params) {
		super(params);
		this.temporizador = new TimerController(params);
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
				Boolean[] guardas = super.getGuardas();
				if(!guardas[transicion]) {
					guardas[transicion] = true;
					super.setGuardas(guardas);
					estadoDeTransicion = ShootingStates.FAIL;
					break;
				} else {
					super.disparar(transicion);
					temporizador.setNuevoTimeStamp(super.getSensibilizadasPorMarca());
					temporizador.resetEsperando(transicion);
					estadoDeTransicion = ShootingStates.SUCCESS;
					break;
				}
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
