package com.unc.concurrente.monitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RDP {
	
	private int plazas, transiciones;
	private static final Integer[] M0 = {0, 0, 0, 0, 1, 1, 1, 3, 0, 0, 30, 0, 30, 0, 0, 0, 1, 1, 60, 0, 0, 0, 1, 0, 1, 0, 1, 5}; /* marcado inicial:contiene el estado inical de la red.*/
	private Integer[] m_actual;
	private Integer[][] insidencia;/* Contiene las relaciones entre las plazas y las transiciones*/
	private boolean guardaT7;

	public RDP() {
		this.plazas = 28;
		this.transiciones = 20;
		this.m_actual = M0;
		this.insidencia = this.cargarMatriz("src/main/resources/com/unc/concurrente/matrices/m_i.txt");
		this.guardaT7 = false;

	}
	
	public RDP(Integer[][] incidencia, Integer[] marcado, int plazas, int transiciones) {
		this.insidencia = incidencia;
		this.plazas = plazas;
		this.transiciones = transiciones;
		this.m_actual = marcado;
	}
	
	/**
	 * Devuelve el estado actual de la Red de Petri.
	 * @return El vector de estado de la Red de Petri.
	 */
	public Integer[] getM_actual(){
		return m_actual;
	}
	
	/**
	 * Intenta disparar una transicion de la Red de Petri.
	 * @param transicion: la transicion que se intenta disparar
	 * @return true si la transicion pudo ser disparada, de lo contrario false.
	 */
	public boolean disparar(int transicion){
		Integer[] cTransicion = obtenerColumna(transicion);
		Integer[] aux = null;
			
		aux = sumar(m_actual, cTransicion);
		
		if(transicion == 7 && aux[transicion] > 0 && guardaT7 == false) {
			guardaT7 = true;
			return false;
		}
		
		for(int i = 0; i < plazas; i++){
			if(aux[i] < 0){
				return false;
			}
		}
		m_actual = aux;
		return true;
	}
	
	/**
	 * Genera un vector con todas las transiciones sensibilizadas de la Red de Petri.
	 * @return un vector con un true en el lugar correspondiente a una transicion sensibilizada, y false
	 * en el lugar correspondiente a una transicion que NO esta sensibilizada.
	 */
	public Boolean[] sensibilizadas(){
		Boolean[] aux = new Boolean[transiciones];
		
		for(int i = 0; i < transiciones; i++){
			Integer[] suma = sumar(m_actual, obtenerColumna(i));
			
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
	
	/**
	 * Carga una matriz desde un archivo
	 * @param direccion: la direccion en la que se encuentra el archivo.
	 * @return una matriz cargada con los datos del archivo.
	 */
	private Integer[][] cargarMatriz(String direccion){
		Scanner scanner = null;
		Integer[][] aux = new Integer[plazas][transiciones];
		
		try {
			scanner = new Scanner(new File(direccion));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while(scanner.hasNextInt()){
			for(int i =0; i < aux.length; i++){
				for(int j =0; j < aux[0].length; j++){
					if(scanner.hasNextInt())
						aux[i][j] = scanner.nextInt();
				}
			}
		}
		return aux;
	}
}