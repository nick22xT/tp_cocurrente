package Monitor;

public class Cola {
	private boolean empty;
	private int contador; //lleva la cuenta de la cantidad de hilos suspendidos en la cola
	
	public Cola(){
		this.empty = false;
		this.contador = 0;
	}
	
	/**
	 * Permite ver si la cola esta vacia o contiene algun elemento en su interior.
	 * @return true si hay un elemento en su interior, de lo contrario devuelve false.
	 */
	public boolean quienesEstan(){
		return empty;
	}
	
	public synchronized void acquire(){
		this.empty = true;
		this.contador++;
		try{
			wait();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public synchronized void release(){
		if(empty){
			this.contador--;
			if(contador == 0){
				this.empty = false;
				notify();
				return;
			}else{
				notify();
				return;
			}
		}
	}
		
		
		

}
