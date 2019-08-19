package com.unc.concurrente;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.unc.concurrente.filewriter.ManejadorDeArchivo;
import com.unc.concurrente.monitor.GestorDeMonitor;
import com.unc.concurrente.rdp.RDPTemporal;
import com.unc.concurrente.recursos.Entrada;
import com.unc.concurrente.recursos.RampaBajada;
import com.unc.concurrente.recursos.RampaSubida;
import com.unc.concurrente.recursos.Salida;
import com.unc.concurrente.recursos.ZonaDeCaja;

public class Application {
	
	private static ManejadorDeArchivo manejador;
    private static GestorDeMonitor monitor;
	private static Thread entradaUno;
	private static Thread entradaDos;
	private static Thread entradaTres;
	private static Thread entradaPisoUno;
	private static Thread entradaPisoDos;
	private static Thread rampaBajadaUno;
	private static Thread rampaBajadaDos;
	private static Thread zonaDeCaja1;
	private static Thread zonaDeCaja2;
	private static Thread salidaUno;
	private static Thread salidaDos;

	public static void main(String[] args) throws IOException {
		manejador = new ManejadorDeArchivo();
	    monitor = new GestorDeMonitor(new RDPTemporal(), manejador);
		
		entradaUno = new Thread(new Entrada(monitor, 0, 3, "ENTRADA 1"));
		entradaDos = new Thread(new Entrada(monitor, 1, 4, "ENTRADA 2"));
		entradaTres = new Thread(new Entrada(monitor, 2, 5, "ENTRADA 3"));
		
		entradaPisoUno = new Thread(new RampaSubida(monitor, 6, 8, "RAMPA 1", "PISO 1"));
		entradaPisoDos = new Thread(new RampaSubida(monitor, 7, 9, "RAMPA 2", "PISO 2"));
				
		rampaBajadaUno = new Thread(new RampaBajada(monitor, 10, 12, "PISO 1", "RAMPA 1"));
		rampaBajadaDos = new Thread(new RampaBajada(monitor, 11, 13, "PISO 2", "RAMPA 2"));
		
		zonaDeCaja1 = new Thread(new ZonaDeCaja(monitor, 14, "SALIDA 1"));
		zonaDeCaja2 = new Thread(new ZonaDeCaja(monitor, 15, "SALIDA 2"));
		
		salidaUno = new Thread(new Salida(monitor, 16, 18, "SALIDA 1"));
		salidaDos = new Thread(new Salida(monitor, 17, 19, "SALIDA 2"));
		
		entradaUno.start();
		entradaDos.start();
		entradaTres.start();
		
		entradaPisoUno.start();
		
		rampaBajadaUno.start();
		rampaBajadaDos.start();
		
		zonaDeCaja1.start();
		zonaDeCaja2.start();
		
		salidaUno.start();
		salidaDos.start();
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		entradaPisoDos.start();
	}
	
	public static void interrumpirMonitor() {
		try {
			entradaUno.interrupt();
		} catch(Exception e) {
			
		}
		
        try {
			entradaDos.interrupt();
		} catch(Exception e) {
			
		}
        
        try {
			entradaTres.interrupt();
		} catch(Exception e) {
			
		}
		
        try {
			entradaPisoUno.interrupt();
		} catch(Exception e) {
			
		}
		
        try {
			entradaPisoDos.interrupt();
		} catch(Exception e) {
			
		}

		try {
			rampaBajadaUno.interrupt();
		} catch(Exception e) {
			
		}
		try {
			rampaBajadaDos.interrupt();
		} catch(Exception e) {
			
		}
		
		try {
			zonaDeCaja1.interrupt();
		} catch(Exception e) {
			
		}
		
		try {
			zonaDeCaja2.interrupt();
		} catch(Exception e) {
			
		}
		
		try {
			salidaUno.interrupt();
		} catch(Exception e) {
			
		}
		try {
			salidaDos.interrupt();
		} catch(Exception e) {
			
		}
		
		manejador.escribirArchivo();
	}
}

