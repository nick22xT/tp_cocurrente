package com.unc.concurrente.rdp;

import com.unc.concurrente.model.ParametrosIniciales;

public class RDP {
	
	private int plazas, transiciones;
	private Integer[] marcaInicial; /* marcado inicial:contiene el estado inical de la red.*/
	private Integer[] marcaActual;
	private Boolean[] guardas;
	private Integer[][] insidencia;/* Contiene las relaciones entre las plazas y las transiciones*/

	public RDP(ParametrosIniciales params) {
		this.plazas = params.getPlazas();
		this.transiciones = params.getTransiciones();
		this.marcaInicial = params.getM0();
		this.marcaActual = marcaInicial;
		this.insidencia = params.getIncidencia();
		this.guardas = params.getGuardas();

	}

	/**
	 * Devuelve el estado actual de la Red de Petri.
	 * @return El vector de estado de la Red de Petri.
	 */
	public Integer[] getM_actual(){
		return marcaActual;
	}
	
	/**
	 * Intenta disparar una transicion de la Red de Petri.
	 * @param transicion: la transicion que se intenta disparar
	 * @return true si la transicion pudo ser disparada, de lo contrario false.
	 */
	public boolean disparar(int transicion){
		Integer[] cTransicion = obtenerColumna(transicion);
		Integer[] aux = null;
		Boolean[] sensibilizadasPorMarca = this.getSensibilizadasPorMarca();
			
		if(sensibilizadasPorMarca[transicion]) {
			aux = sumar(marcaActual, cTransicion);
			marcaActual = aux;
			return true;
		} else {
			return false;
		}
 	}
	
	/**
	 * Genera un vector con todas las transiciones sensibilizadas de la Red de Petri.
	 * @return un vector con un true en el lugar correspondiente a una transicion sensibilizada, y false
	 * en el lugar correspondiente a una transicion que NO esta sensibilizada.
	 */
	public Boolean[] getSensibilizadasPorMarca(){
		Boolean[] aux = new Boolean[transiciones];
		
		for(int i = 0; i < transiciones; i++){
			Integer[] suma = sumar(marcaActual, obtenerColumna(i));
			
			for(int j = 0; j < plazas; j++){
				if(suma[j] < 0){
					aux[i] = false;
					break;
				}else{
					aux[i] = true;
				}
			}
		}
		return aux;
	}
	
	/**
	 * Devuelve el numero de plazas de la Red de Petri.
	 * @return el numero de plazas de la Red de Petri.
	 */
	public int getPlazas(){
		return plazas;
	}
	
	/**
	 * Devuelve el numero de transiciones de la Red de Petri.
	 * @return el numero de transiciones de la Red de Petri.
	 */
	public int getTransiciones(){
		return transiciones;
	}
	
	/**
	 * Obtiene la columna de la matriz de insidencia correspondiente a una transicion
	 * en particular.
	 * @param transicion
	 * @return un vector que contiene los valores de la columna de 
	 * la matriz de insidencia correspondiente a la transicion deseada. 
	 */
	public Integer[] obtenerColumna(int transicion){
		Integer[] aux = new Integer[plazas];
		
        for(int i = 0; i < plazas; i++){
        	aux[i] = this.insidencia[i][transicion];
        }
		return aux;
	}
	
	/**
	 * Suma dos vectores coordenada a coordenada.
	 * @param a
	 * @param b
	 * @return un vector que contiene la suma coordenada a coordenada de los vectores a y b.
	 * @throws Exception 
	 */
	public Integer[] sumar(Integer[] a, Integer[] b){
		if(a.length != b.length)
			throw new IllegalArgumentException("Las dimensiones de los vectores no son iguales");
		
		Integer[] aux = new Integer[a.length];
		
		for(int i = 0; i < aux.length; i++){
			aux[i] = a[i] + b[i];
		}
		return aux;
	}
	
	public Boolean[] getGuardas() {
		return guardas;
	}

	public void setGuardas(Boolean[] guardas) {
		this.guardas = guardas;
	}
}