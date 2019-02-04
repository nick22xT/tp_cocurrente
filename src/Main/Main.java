package Main;

import Monitor.*;
import Recursos.*;

public class Main {

	public static void main(String[] args) {
		GestorDeMonitor monitor = new GestorDeMonitor(new RDP(), new Politicas());
		
		Thread entradaUno = new Thread(new Entrada(monitor, 0, "Entrada 1"));
		Thread entradaDos = new Thread(new Entrada(monitor, 1, "Entrada 2"));
		Thread entradaTres = new Thread(new Entrada(monitor, 2, "Entrada 3"));
		
		Thread barreraUno = new Thread(new BarreraDeEntrada(monitor, 3, "Barrera 1"));
		Thread barreraDos = new Thread(new BarreraDeEntrada(monitor, 4, "Barrera 2"));
		Thread barreraTres = new Thread(new BarreraDeEntrada(monitor, 5, "Barrera 3"));
		
		Thread rampaSubidaUno = new Thread(new RampaSubida(monitor, 6, "Rampa 1"));
		Thread rampaSubidaDos = new Thread(new RampaSubida(monitor, 7, "Rampa 2"));
		
		Thread entrandoPisoUno = new Thread(new Estacionando(monitor, 8, "Piso 1"));
		Thread entrandoPisoDos = new Thread(new Estacionando(monitor, 9, "Piso 2"));
		
		Thread rampaBajadaUno = new Thread(new RampaBajada(monitor, 10, 12, "Salida-Rampa 1", "Salida-Piso 1"));
		Thread rampaBajadaDos = new Thread(new RampaBajada(monitor, 11, 13, "Salida-Rampa 2", "Salida-Piso 2"));
		
		Thread barreraSalidaUno = new Thread(new BarreraDeSalida(monitor, 14, "Salida 1"));
		Thread barreraSalidaDos = new Thread(new BarreraDeSalida(monitor, 15, "Salida 2"));
		
		Thread salidaUno = new Thread(new Salida(monitor, 16, "Salida 1"));
		Thread salidaDos = new Thread(new Salida(monitor, 17, "Salida 2"));
		
		entradaUno.start();
		entradaDos.start();
		entradaTres.start();
		
		barreraUno.start();
		barreraDos.start();
		barreraTres.start();
		
		rampaSubidaUno.start();
		rampaSubidaDos.start();
		
		entrandoPisoUno.start();
		entrandoPisoDos.start();
		
		rampaBajadaUno.start();
		rampaBajadaDos.start();
		
		barreraSalidaUno.start();
		barreraSalidaDos.start();
		
		salidaUno.start();
		salidaDos.start();
	}
	
	
}

