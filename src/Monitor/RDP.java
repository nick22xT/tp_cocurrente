package Monitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RDP {
	
	private int plazas, transiciones;
	
	private int[] m0 = {0, 20, 0, 0, 0, 0, 0, 1, 1, 1}; 
	/* marcado inicial: contiene el 
	 * estado inical de la red.*/
	
	private int[] m_actual;
	
	private int[][] pre;
	/* matriz pre*/
	
	private int[][] post; 
	/* matriz post*/
	
	private int[][] inhibicion; 
	/* matriz de inhibicion: relaciona las plazas que conectan las transiciones
	 *  con un brazo inhibidor, los terminos de la matriz son uno si hay brazo inhibidor
	 *  de la plaza a la transicion y cero si no lo hay.*/

	public RDP() {
		
		this.plazas = 10;
		this.transiciones = 8;
		
		this.pre = this.cargarMatriz("src/Matrices/m_pre.txt");
		this.post = this.cargarMatriz("src/Matrices/m_post.txt");
		this.inhibicion = this.cargarMatriz("src/Matrices/matriz_h.txt");

	}
	
	/**
	 * Intenta disparar una transicion de la Red de Petri.
	 * @param transicion: la transicion que se intenta disparar
	 * @return true si la trnasicion pudo ser disparada, de lo contrario false.
	 */
	public boolean disparar(int transicion){
		
		int[] vDisparo = new int[transiciones];
		
		for(int i = 0; i < transiciones; i++){
			
			if(i != transicion){
				
				vDisparo[i] = 0;
			}else{
				
				vDisparo[i] = 1;
			}
		}
		
		//ejecutar la ecuacion de estado
		
		return false;
	}
	
	/**
	 * Genera un vector con todas las transiciones sensibilizadas de la Red de Petri.
	 * @return un vector con un 1 en el lugar correspondiente a una transicion sensibilizada, y un 0
	 * en el lugar correspondiente auna transicion que NO esta sensibilizada.
	 */
	public int[] sensibilizadas(){
		
		return null;
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
