package Main;

import Monitor.*;

public class Main {

	public static void main(String[] args) {
		
		RDP red = new RDP();
		Politicas politica = new Politicas(red.getTransiciones());
		
		GestorDeMonitor g = new GestorDeMonitor(red, politica);
		
		Thread t = new Thread(new Runnable(){
			public void run(){
				while(true){
					
					g.dispararTransicion(0);
					System.out.println("Productor 1: Poniendo en el buffer.");
					g.dispararTransicion(3);
					g.dispararTransicion(4);
				}
			}
		});
		
		t.start();
		
		Thread t2 = new Thread(new Runnable(){
			public void run(){
				while(true){
					
					g.dispararTransicion(0);
					System.out.println("Productor 2: Poniendo en el buffer.");
					g.dispararTransicion(3);
					g.dispararTransicion(4);
				}
			}
		});
		
		t2.start();
		
		Thread t1 = new Thread(new Runnable(){
			public void run(){
				while(true){
					g.dispararTransicion(1);
					System.out.println("Consumidor 1: Sacando del buffer.");
					g.dispararTransicion(2);
					g.dispararTransicion(5);
				}
			}
		});
		
		
		t1.start();
		
		Thread t3 = new Thread(new Runnable(){
			public void run(){
				while(true){
					g.dispararTransicion(1);
					System.out.println("Consumidor 2: Sacando del buffer.");
					g.dispararTransicion(2);
					g.dispararTransicion(5);
				}
			}
		});
		
		
		t3.start();
	}
	
	
}

