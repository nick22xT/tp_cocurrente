package Exceptions;

public class InvCuatroException extends RuntimeException {
	
	private static final long serialVersionUID = -9138971081911280574L;
	private String mensaje = "Violacion del invariante Cuatro";
	
	
    
    public String getMessaje() {
    	
    	return mensaje;
    }
    
    
}