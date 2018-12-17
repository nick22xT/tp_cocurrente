package Exceptions;

public class InvUnoException extends RuntimeException {
	
	private static final long serialVersionUID = -2916048719059154339L;
    private String mensaje = "Violacion del invariante Uno";
    
    
    
    public String getMessaje() {
    	
    	return mensaje;
    }
    
}
