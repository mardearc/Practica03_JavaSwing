package model;

public class NumeroNoValidoException extends Exception{

	
	public NumeroNoValidoException() {}
	
	public NumeroNoValidoException(String mensaje){
		super(mensaje);
	}
}
