package model;

public class NumeroNoValidoException extends Exception{

	private static String MENSAJE = "Numero mayor que 1000 o menor que 0";
	
	public NumeroNoValidoException() {}
	
	public NumeroNoValidoException(String MENSAJE){
		super(MENSAJE);
	}
}
