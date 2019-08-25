package com.unc.concurrente;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.unc.concurrente.monitor.GestorDeMonitor;
import com.unc.concurrente.rdp.RDPTemporal;
import com.unc.concurrente.recursos.Entrada;
import com.unc.concurrente.recursos.RampaBajada;
import com.unc.concurrente.recursos.RampaSubida;
import com.unc.concurrente.recursos.Salida;
import com.unc.concurrente.recursos.ZonaDeCaja;
import com.unc.concurrente.utils.FileManagement;
import com.unc.concurrente.utils.XMLReader;

public class Application {
	
	private static final String XML_CONFIGURAION_FILE_PATH = "src/main/resources/PetriNetConfiguration.xml";
	private static int sleep = 500;
	private static FileManagement manejador;
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
		if(args.length > 0) {
			if("-d".equals(args[0])) {
				sleep = 0;
			}
		}
		
		manejador = new FileManagement();
	    monitor = new GestorDeMonitor(new RDPTemporal(XMLReader.getParametrosIniciales(XML_CONFIGURAION_FILE_PATH)), manejador);
		
		entradaUno = new Thread(new Entrada(monitor, 0, 3, sleep, "ENTRADA 1"));
		entradaDos = new Thread(new Entrada(monitor, 1, 4, sleep, "ENTRADA 2"));
		entradaTres = new Thread(new Entrada(monitor, 2, 5, sleep, "ENTRADA 3"));
		
		entradaPisoUno = new Thread(new RampaSubida(monitor, 6, 8, sleep, "RAMPA 1", "PISO 1"));
		entradaPisoDos = new Thread(new RampaSubida(monitor, 7, 9, sleep, "RAMPA 2", "PISO 2"));
				
		rampaBajadaUno = new Thread(new RampaBajada(monitor, 10, 12, sleep, "PISO 1", "RAMPA 1"));
		rampaBajadaDos = new Thread(new RampaBajada(monitor, 11, 13, sleep, "PISO 2", "RAMPA 2"));
		
		zonaDeCaja1 = new Thread(new ZonaDeCaja(monitor, 14, sleep, "SALIDA 1"));
		zonaDeCaja2 = new Thread(new ZonaDeCaja(monitor, 15, sleep, "SALIDA 2"));
		
		salidaUno = new Thread(new Salida(monitor, 16, 18, sleep, "SALIDA 1"));
		salidaDos = new Thread(new Salida(monitor, 17, 19, sleep, "SALIDA 2"));
		
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
		while(!entradaUno.isInterrupted()) {
			try {
				entradaUno.interrupt();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
        while(!entradaDos.isInterrupted()) {
        	try {
    			entradaDos.interrupt();
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
        }
        
        while(!entradaTres.isInterrupted()) {
        	try {
    			entradaTres.interrupt();
    		} catch(Exception e) {
    			
    		}
        }
		
        while(!entradaPisoUno.isInterrupted()) {
        	try {
    			entradaPisoUno.interrupt();
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
        }
		
        while(!entradaPisoDos.isInterrupted()) {
        	try {
    			entradaPisoDos.interrupt();
    		} catch(Exception e) {
    			
    		}
        }

		while(!rampaBajadaUno.isInterrupted()) {
			try {
				rampaBajadaUno.interrupt();
			} catch(Exception e) {
				
			}
		}
		
		while(!rampaBajadaDos.isInterrupted()) {
			try {
				rampaBajadaDos.interrupt();
			} catch(Exception e) {
				
			}
		}
		
		while(!zonaDeCaja1.isInterrupted()) {
			try {
				zonaDeCaja1.interrupt();
			} catch(Exception e) {
				
			}
		}
		
		while(!zonaDeCaja2.isInterrupted()) {
			try {
				zonaDeCaja2.interrupt();
			} catch(Exception e) {
				
			}
		}
		
		while(!salidaUno.isInterrupted()) {
			try {
				salidaUno.interrupt();
			} catch(Exception e) {
				
			}
		}
		
		while(!salidaDos.isInterrupted()) {
			try {
				salidaDos.interrupt();
			} catch(Exception e) {
				
			}
		}
		
		manejador.escribirArchivo();
	}
}

