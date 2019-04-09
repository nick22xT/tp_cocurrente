package Main;

import Monitor.*;
import Recursos.*;

public class Main {

	public static void main(String[] args) {
		GestorDeMonitor monitor = new GestorDeMonitor(new RDP());
		
		Thread entradaUno = new Thread(new Entrada(monitor, 0, "ENTRADA 1"));
		Thread entradaDos = new Thread(new Entrada(monitor, 1, "ENTRADA 2"));
		Thread entradaTres = new Thread(new Entrada(monitor, 2, "ENTRADA 3"));
		
		Thread barreraUno = new Thread(new BarreraDeEntrada(monitor, 3, "BARRERA 1"));
		Thread barreraDos = new Thread(new BarreraDeEntrada(monitor, 4, "BARRERA 2"));
		Thread barreraTres = new Thread(new BarreraDeEntrada(monitor, 5, "BARRERA 3"));
		
		Thread entradaPisoUno = new Thread(new RampaSubida(monitor, 6, 8, "RAMPA 1", "PISO 1"));
		Thread entradaPisoDos = new Thread(new RampaSubida(monitor, 7, 9, "RAMPA 2", "PISO 2"));
				
		Thread rampaBajadaUno = new Thread(new RampaBajada(monitor, 10, 12, "PISO 1", "RAMPA 1"));
		Thread rampaBajadaDos = new Thread(new RampaBajada(monitor, 11, 13, "PISO 2", "RAMPA 2"));
		
		Thread zonaDeCaja1 = new Thread(new ZonaDeCaja(monitor, 14, "SALIDA 1"));
		Thread zonaDeCaja2 = new Thread(new ZonaDeCaja(monitor, 15, "SALIDA 2"));
		
		Thread barreraSalidaUno = new Thread(new BarreraDeSalida(monitor, 16, "SALIDA 1"));
		Thread barreraSalidaDos = new Thread(new BarreraDeSalida(monitor, 17, "SALIDA 2"));
		
		Thread salidaUno = new Thread(new Salida(monitor, 18, "SALIDA 1"));
		Thread salidaDos = new Thread(new Salida(monitor, 19, "SALIDA 2"));
		
		entradaUno.start();
		entradaDos.start();
		entradaTres.start();
		
		barreraUno.start();
		barreraDos.start();
		barreraTres.start();
		
		entradaPisoUno.start();
		
		rampaBajadaUno.start();
		rampaBajadaDos.start();
		
		zonaDeCaja1.start();
		zonaDeCaja2.start();
		
		barreraSalidaUno.start();
		barreraSalidaDos.start();
		
		salidaUno.start();
		salidaDos.start();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//entradaPisoDos.start();
	}
	
	
}

