package com.unc.concurrente.mocks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.unc.concurrente.rdp.RDP;

public class RDPMock {
	
	private static final int TRANSICIONES = 6;
	private static final int PLAZAS = 9;
	private static final Integer[] M0 = new Integer[]{1, 1, 0, 0, 0, 0, 1, 0, 5};
	private static final Integer[][] INCIDENCIA = cargarMatriz("src/test/resources/m_i_test.txt", PLAZAS, TRANSICIONES);
	
	public static RDP getRDP() {
		return new RDP(INCIDENCIA, M0, PLAZAS, TRANSICIONES);
	}
	
	private static Integer[][] cargarMatriz(String direccion, int plazas, int transiciones) {
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
