package Main;

import Monitor.*;

public class Main {

	public static void main(String[] args) {
		
		RDP red = new RDP();
		Politicas politica = new Politicas(red.getTransiciones());
		
		GestorDeMonitor g = new GestorDeMonitor(red, politica);
		
		Thread t = new Thread(new Runnable(){
			public void run(){
				
				int cont = 0;
				
				while(true){
					
					g.dispararTransicion(0);
					System.out.printf(cont + " Productor %s: Poniendo en el buffer.\n", Thread.currentThread().getName());
					g.dispararTransicion(3);
					g.dispararTransicion(4);
					cont++;
				}
			}
		});
		
		t.start();
		
		
		
		Thread t1 = new Thread(new Runnable(){
			public void run(){
				
				int cont = 0;
				
				while(true){
					g.dispararTransicion(1);
					System.out.printf(cont + " Consumidor %s: Sacando del buffer.\n", Thread.currentThread().getName());
					g.dispararTransicion(2);
					g.dispararTransicion(5);
					cont++;
				}
			}
		});
		
		
		t1.start();
		
	}
	
	
}

