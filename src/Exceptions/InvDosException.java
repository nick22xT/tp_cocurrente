package Exceptions;

public class InvDosException extends RuntimeException {
	
	private static final long serialVersionUID = -708811217343420261L;
	private String mensaje = "Violacion del invariante Dos";
    
    public String getMessaje() {
    	
    	return mensaje;
    }
    
    
}