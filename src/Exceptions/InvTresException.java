package Exceptions;

public class InvTresException extends RuntimeException {
	
	private static final long serialVersionUID = -1918237751459671327L;
	private String mensaje = "Violacion del invariante tres";
    
    public String getMessaje() {
    	
    	return mensaje;
    }
    
    
}