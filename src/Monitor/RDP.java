package Monitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Exceptions.*;

public class RDP {
	
	private int plazas, transiciones;
	private final int[] M0 = {0,1,0,0,0,0}; /* marcado inicial:
	contiene el estado inical de la red.*/
	private int[] m_actual;
	private int[][] insidencia;/* Contiene las relaciones entre las plazas y 
	las transiciones*/
	private int[][] inhibicion; 
	/* matriz de inhibicion: relaciona las plazas que conectan las transiciones
	 *  con un brazo inhibidor, los terminos de la matriz son uno si hay brazo inhibidor
	 *  de la plaza a la transicion y cero si no lo hay.*/

	public RDP() {
		this.plazas = 6;
		this.transiciones = 4;
		this.m_actual = M0;
		this.insidencia = this.cargarMatriz("src/Matrices/m_i.txt");
		this.inhibicion = this.cargarMatriz("src/Matrices/matriz_h.txt");

	}
	/**
	 * Devuelve el estado actual de la Red de Petri.
	 * @return El vector de estado de la Red de Petri.
	 */
	public int[] getM_actual(){
		return m_actual;
	}
	
	/**
	 * Intenta disparar una transicion de la Red de Petri.
	 * @param transicion: la transicion que se intenta disparar
	 * @return true si la transicion pudo ser disparada, de lo contrario false.
	 */
	public boolean disparar(int transicion){
		int[] cTransicion = obtenerColumna(transicion);
		int[] aux = null;
			
		aux = sumar(m_actual, cTransicion);
		
		try {
			this.validarInvariantes(aux);
		}catch(RuntimeException e) {
			e.getMessage();
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
	public boolean[] sensibilizadas(){
		
		boolean[] aux = new boolean[transiciones];
		
		for(int i = 0; i < transiciones; i++){
			
			int[] suma = sumar(m_actual, obtenerColumna(i));
			
			try {
				this.validarInvariantes(suma);
			}catch(RuntimeException e) {
				e.getMessage();
			}
			
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
	public int[] obtenerColumna(int transicion){
		int[] aux = new int[plazas];
		
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
	public int[] sumar(int[] a, int[] b){
		if(a.length != b.length){
			throw new IllegalArgumentException("Las dimensiones de los vectores no son iguales");
		}
		
		int[] aux = new int[a.length];
		
		for(int i = 0; i < aux.length; i++){
			aux[i] = a[i] + b[i];
		}
		return aux;
	}
	
	public void validarInvariantes(int[] m_actual) {
		boolean invUno = m_actual[1] + m_actual[3] + m_actual[5] == 1;
		boolean invDos = m_actual[0] + m_actual[2] + m_actual[4] == 1;
		boolean invTres = m_actual[2] + m_actual[3] + m_actual[6] == 1;
		boolean invCuatro = m_actual[2] + m_actual[3] + m_actual[7] + m_actual[8] == 2;
		
		if(!invUno) {
			throw new InvUnoException();
		}
		
        if(!invDos) {
        	throw new InvDosException();
		}
        
        if(!invTres) {
        	throw new InvTresException();
		}
        
        if(!invCuatro) {
        	throw new InvCuatroException();
		}

	}
	
	/**
	 * Carga una matriz desde un archivo
	 * @param direccion: la direccion en la que se encuentra el archivo.
	 * @return una matriz cargada con los datos del archivo.
	 */
	private int[][] cargarMatriz(String direccion){
		Scanner scanner = null;
		int [][] aux = new int [plazas][transiciones];
		
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
					else 
						break; 
				}
			}
		}
		return aux;
	}
}
