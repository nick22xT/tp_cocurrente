package com.unc.concurrente.monitor;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import com.unc.concurrente.Application;

public class MonitorSystemTest {
	
	private String FILE_PATH = "D:\\2do Semestre 2018\\Programacion Concurrente\\Trabajo Final\\Tabajo Final Monitor\\src\\main\\resources\\transicionLog.txt";
	private FileReader archivo;
	private String[] arregloSecuencia;
	
	String[] tInvariantes1={"0","3","6","8","10","12","14","16","18"};
	String[] tInvariantes2={"0","3","7","9","11","13","15","16","18"};
	
	@Before
	public void correr() {
	
		try {
			Application.main(null);
			TimeUnit.SECONDS.sleep(300);
			Application.interrumpirMonitor();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			archivo = new FileReader("D:\\2do Semestre 2018\\Programacion Concurrente\\Trabajo Final\\Tabajo Final Monitor\\src\\main\\resources\\transicionLog.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BufferedReader buffer = new BufferedReader(archivo);
		try {
			String secuencia=buffer.readLine();
			arregloSecuencia = secuencia.split("-");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void verificarInvariantes()
	{
		assertTrue(recorrerArreglo(tInvariantes1));
		assertTrue(recorrerArreglo(tInvariantes2)); 
	}
	
	public boolean recorrerArreglo(String[] arreglo) // devuelve true si se encontro la secuencia
	{	
		arregloSecuencia = leerArchivo().split("-");
		int cursor=0;

		for(int i=0; i<arregloSecuencia.length; i++)
		{
			if(arregloSecuencia[i].equals(arreglo[cursor]))
			{
				cursor++;
				
				if(cursor==arreglo.length)
				{
					return true;
				}
			}
		}
		
		return false;
	}
	
	public String leerArchivo() {
		try {
			archivo = new FileReader("D:\\2do Semestre 2018\\Programacion Concurrente\\Trabajo Final\\Tabajo Final Monitor\\src\\main\\resources\\transicionLog.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BufferedReader buffer = new BufferedReader(archivo);
		try {
			return buffer.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
