package com.unc.concurrente;

import java.util.ArrayList;
import java.util.List;

public class TInvariantesStateMachine {
	
	private List<String[]> t_invariants;
	private List<Road> roads;

	public TInvariantesStateMachine(List<String[]> t_invariants) {
		this.t_invariants = t_invariants;
		this.roads = new ArrayList<>();
	}
	
	
	public Boolean validatTInvariantes(String[] transitions) {
		String transition;
		List<String[]> invariantCandidates;
		
		 for(int i = 0; i < transitions.length; i++) {
			 transition = transitions[i];
			 invariantCandidates = buscarInvariantesCandidatos(transition);
			 
			 if(!ubicarTransicion(invariantCandidates, transition))
				 return false; 
			 
			 invariantCandidates.clear();
			 transitions[i] = null;
		 }
		 
		 return validarSecuenciaVacia(transitions) && validarCaminos();
	}

	/**
	 * Busca todos los invariantes candidatos a los que pueda pertenecer la transicion.
	 * @param transition
	 * @return
	 */
	List<String[]> buscarInvariantesCandidatos(String transition) {
		String[] invariant;
		List<String[]> invariantCandidates = new ArrayList<>();
		
		for(int i = 0; i < t_invariants.size(); i++) {
			invariant = t_invariants.get(i);
			for(int j = 0; j < invariant.length; j++) {
				if(transition.equals(invariant[j])) {
					invariantCandidates.add(invariant);
					break;
				}
			}
		}
		
		return invariantCandidates; // si llega a este punto, error
		
	}
	
	/**
	 * Acomoda la transicion en cuestion dentro de algun camino,
	 *  o inicia un camino nuevo si esta es inicio de algun invariante candidato.
	 * @param invariantCandidates
	 * @param transition
	 * @throws Exception 
	 */
	Boolean ubicarTransicion(List<String[]> invariantCandidates, String transition) {
		if(roads.isEmpty() && !esInicioDeCamino(transition, invariantCandidates)) {
			throw new IllegalStateException("No se cumple con los T-Invariantes. ERROR");
		} else if(esInicioDeCamino(transition, invariantCandidates)) {
			Road road = new Road();
			road.getRoad().add(transition);
			roads.add(road);
			return true;
		} else {
			return elegirCamino(invariantCandidates, transition);
		}
	}
	
	/**
	 * Comprueba si la transicion es una transicion de inicio de algunos de los invariantes candidatos.
	 * @param transition 
	 * @param invariants
	 * @return
	 */
	Boolean esInicioDeCamino(String transition, List<String[]> invariants) {
		for(int i = 0; i < invariants.size(); i++) {
			String[] invariant = invariants.get(i);
			if(invariant[0].equals(transition)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Ubica la transicion dentro de un camino, en funcion de los invariantes candidatos que esa transicion tenga.
	 * @param invariantCandidates
	 * @param transition
	 * @return
	 */
	Boolean elegirCamino(List<String[]> invariantCandidates, String transition) {
		String[] invariant;
		
		for(int i = 0; i < roads.size(); i++) {
			List<String> road = roads.get(i).getRoad();
			for(int j = 0; j < invariantCandidates.size(); j++) {
				invariant = invariantCandidates.get(j);
				if(road.size() != invariant.length) {
					if(road.get(road.size() - 1).equals(invariant[road.size() - 1]) && invariant[road.size()].equals(transition)) {
						roads.get(i).getRoad().add(transition);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Valida que todos los caminos completos pertenezcan a un invariante y que los caminos incompletos
	 * tengan al menos un invariante candidato.
	 * @return
	 */
	Boolean validarCaminos() {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Valida que la secuencia de transiciones de la red de petri haya quedado vacia,
	 * es decir que todas las transiciones hayan podido ser ubicadas en un camino.
	 * @param transitions
	 * @return
	 */
	Boolean validarSecuenciaVacia(String[] transitions) {
		for(int i = 0; i < transitions.length; i++) {
			if(transitions[i] != null) {
				return false;
			}
		}
		return true;
	}
}
