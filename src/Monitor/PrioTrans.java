package Monitor;

public enum PrioTrans {
	
	T0(3),T1(0),T2(1),T3(4),T4(5),T5(2);
	
	int numPrioridad;
	
	PrioTrans(int numPrioridad) {
		
		this.numPrioridad = numPrioridad;
	}
	
	public int get() {
		
		return numPrioridad;
	}

}
