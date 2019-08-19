package com.unc.concurrente;

import java.io.IOException;

import com.unc.concurrente.filewriter.ManejadorDeArchivo;
import com.unc.concurrente.monitor.GestorDeMonitor;
import com.unc.concurrente.rdp.RDPTemporal;
import com.unc.concurrente.recursos.Entrada;
import com.unc.concurrente.recursos.RampaBajada;
import com.unc.concurrente.recursos.RampaSubida;
import com.unc.concurrente.recursos.Salida;
import com.unc.concurrente.recursos.ZonaDeCaja;

public class Application {
	static ManejadorDeArchivo manejador = new ManejadorDeArchivo();
    static GestorDeMonitor monitor = new GestorDeMonitor(new RDPTemporal(), manejador);
	
	static Thread entradaUno = new Thread(new Entrada(monitor, 0, 3, "ENTRADA 1"));
	static Thread entradaDos = new Thread(new Entrada(monitor, 1, 4, "ENTRADA 2"));
	static Thread entradaTres = new Thread(new Entrada(monitor, 2, 5, "ENTRADA 3"));
	
	static Thread entradaPisoUno = new Thread(new RampaSubida(monitor, 6, 8, "RAMPA 1", "PISO 1"));
	static Thread entradaPisoDos = new Thread(new RampaSubida(monitor, 7, 9, "RAMPA 2", "PISO 2"));
			
	static Thread rampaBajadaUno = new Thread(new RampaBajada(monitor, 10, 12, "PISO 1", "RAMPA 1"));
	static Thread rampaBajadaDos = new Thread(new RampaBajada(monitor, 11, 13, "PISO 2", "RAMPA 2"));
	
	static Thread zonaDeCaja1 = new Thread(new ZonaDeCaja(monitor, 14, "SALIDA 1"));
	static Thread zonaDeCaja2 = new Thread(new ZonaDeCaja(monitor, 15, "SALIDA 2"));
	
	static Thread salidaUno = new Thread(new Salida(monitor, 16, 18, "SALIDA 1"));
	static Thread salidaDos = new Thread(new Salida(monitor, 17, 19, "SALIDA 2"));

	public static void main(String[] args) throws IOException {
		
		
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
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		entradaPisoDos.start();
	}
	
	public static void interrumpirMonitor() {
		entradaUno.interrupt();
		entradaDos.interrupt();
		entradaTres.interrupt();
		
		entradaPisoUno.interrupt();
		entradaPisoDos.interrupt();
		
		rampaBajadaUno.interrupt();
		rampaBajadaDos.interrupt();
		
		zonaDeCaja1.interrupt();
		zonaDeCaja2.interrupt();
		
		salidaUno.interrupt();
		salidaDos.interrupt();
		
		manejador.escribirArchivo();
	}
}

